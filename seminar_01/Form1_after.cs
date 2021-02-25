using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClientSide
{

    //1. Redenumirea variabilelor intr-un mod sugestiv( nume_nume_...)
    //2. Linia 38 din fisierul before este redundanta (a fost stearsa)
    //3. Spatierea a fost reparata
    //4. Linia 158 din fisierul before se repeta la 161, asadar a fost stearsa. Aceeasi situatie pentru linia 177
    //5. Functia DoWorkOnPackage (linia 105 in fisierul de before, 89 in cel de after) contine prea mult cod, 
    //    ar fi trebuit sparta in mai multe functii mai mici si mai clare


    public partial class Form1 : Form
    {

        TcpClient tcp_client;
        String nume_utilizator;
        List<Produs> oferte;
        List<Produs> produse;
        public Form1()
        {
            InitializeComponent();
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView2.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            tcp_client = new TcpClient();
            produse = new List<Produs>();
            oferte = new List<Produs>();
            IPAddress ipAd = IPAddress.Parse("192.168.3.108");
            int port_number = 8888;
            username dialog = new username();
            dialog.ShowDialog();
            if (dialog.DialogResult == DialogResult.OK)
            {
                label3.Text = "Username: " + dialog.tb_username.Text;
                nume_utilizator = dialog.tb_username.Text;
                try
                {
                    tcp_client.Connect(ipAd, port_number);
                    NetworkStream stream = tcp_client.GetStream();
                    byte[] sendBytes;
                    sendBytes = Encoding.ASCII.GetBytes(nume_utilizator);
                    stream.Write(sendBytes, 0, sendBytes.Length);
                    Thread ReceiveData = new Thread(DataReceive);
                    ReceiveData.Start();

                }
                catch (Exception err)
                {
                    MessageBox.Show(err.Message);
                }
            }
        }

        private void DataReceive()
        {
            NetworkStream stream = tcp_client.GetStream();
            String[] packet;
            while (true)
            {
                try
                {
                    packet = ReadPacket(stream);
                    DoWorkOnPacket(packet);

                }
                catch (Exception err)
                {
                    Console.WriteLine(err.Message);
                }
            }
        }

        private void DoWorkOnPacket(string[] packet)
        {
            switch (packet[0])
            {
                case "update_produs":
                    produse.Clear();
                    oferte.Clear();
                    String owner, name,state;
                    int starting_price, current_price, parser = 2;
                    int x = Int32.Parse(packet[1]);
                    for (int j = 1; j <= x; j++)
                    {
                        name = packet[parser];
                        parser++;
                        owner = packet[parser];
                        parser++;
                        starting_price = Int32.Parse(packet[parser]);
                        parser++;
                        current_price = Int32.Parse(packet[parser]);
                        parser++;
                        state = packet[parser];
                        parser++;
                        Produs produs = new Produs(name, owner, starting_price, current_price,state);
                        if (owner == nume_utilizator)
                        {
                            oferte.Add(produs);
                        }
                        else
                        {
                            produse.Add(produs);
                        }
                    }
                    dataGridView1.Invoke(
                       new MethodInvoker(delegate ()
                       {                         
                           dataGridView1.DataSource = null;
                           dataGridView1.DataSource = produse;
                           dataGridView1.ClearSelection();
                           foreach(DataGridViewRow r in  dataGridView1.Rows)
                           {
                               if (produse.ElementAt(r.Index).State == "Active")
                               {
                                   r.DefaultCellStyle.ForeColor = Color.Green;
                               }
                               else
                                   r.DefaultCellStyle.ForeColor = Color.Red;
                           }

                       }));

                    dataGridView2.Invoke(
                          new MethodInvoker(delegate ()
                          {
                             
                              dataGridView2.DataSource = null;
                              dataGridView2.DataSource = oferte;
                              dataGridView2.ClearSelection();
                          }));
                    break;
                case "notification":
                    MessageBox.Show(packet[1]);
                    break;
            }

        }

        private string[] ReadPacket(NetworkStream stream)
        {
            String[] packet;
            byte[] bytesFrom = new byte[1024];
            stream.Read(bytesFrom, 0, 1024);
            packet = System.Text.Encoding.ASCII.GetString(bytesFrom).Split('$');
            return packet;
        }

        private void btn_add_Click(object sender, EventArgs e)
        {
            AddAuction dialog = new AddAuction();
            dialog.ShowDialog();
            if (dialog.DialogResult == DialogResult.OK)
            {
                String packet = "upload_produs$" + dialog.tb_name.Text + "$" + nume_utilizator + "$" + dialog.tb_startingprice.Text + "$" + dialog.tb_startingprice.Text + "$";

                SendPacket(packet);
            }
        }

        private void SendPacket(string packet)
        {
            byte[] send_byte = new byte[1024];
            send_byte = Encoding.ASCII.GetBytes(packet);
            NetworkStream stream = tcp_client.GetStream();
            stream.Write(send_byte, 0, send_byte.Length);
        }

        private void dataGridView1_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {               
                Produs produs = produse.ElementAt(dataGridView1.CurrentCell.RowIndex);
                if (produs.State == "Expired")
                {
                    MessageBox.Show("This offer expired!");
                }
                else
                {
                    BidOnOffer dialog = new BidOnOffer(produs.Current_price);
                    dialog.ShowDialog();
                    if (dialog.DialogResult == DialogResult.OK)
                    {
                        String packet = "bid$" + produs.Name + "$" + dialog.tb_bid.Text + "$" + nume_utilizator + "$";
                        produs = null;
                        SendPacket(packet);
                    }
                }
            }
            catch
            {
                dataGridView1.ClearSelection();
            }
            }
        }
    }

