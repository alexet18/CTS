package ro.ase.acs.cts.main;

import ro.ace.acs.cts.logger.LoggerSingleton;
import ro.ace.acs.cts.logger.LoggerSingletonV2;
import ro.ace.acs.cts.logger.LoggerSingletonWithEnum;

public class Gui {
public static void main(String[] args)
{
//	Logger logger = new Logger();
//	logger.log("GUI opened");
	
	LoggerSingleton logger = LoggerSingleton.getInstance();
	logger.log("GUI singleton");
	
	LoggerSingletonWithEnum.INSTANCE.log("Enum");
	LoggerSingletonV2.instance.log("test");
}
}
