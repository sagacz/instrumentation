package com.instrumentacja.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

public class Agent {
    private final static Logger logger = Logger.getLogger("Agent");

    public static void premain(String args, Instrumentation instrumentation){
        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.instrumentacja"))
                .transform(((((builder, typeDescription, classLoader, module) -> {
                    logger.info("Class " + typeDescription);
                    return builder.visit(Advice.to(MyMethodMonitor.class).on(ElementMatchers.any()));
                }))));
        agentBuilder.installOn(instrumentation);
    }

    public static void agentmain(String args, Instrumentation instrumentation){
        premain(args, instrumentation);
    }

}
