package com.instrumentacja.agent;

import net.bytebuddy.asm.Advice;

import java.util.logging.Logger;

public class MyMethodMonitor {


    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Class clazz,
                             @Advice.Origin ("#m") String methodName){
        Logger logger = Logger.getLogger("Monitor");
        logger.info("calling " + methodName);
    }


}
