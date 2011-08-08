/*
 * Copyright 2011 Memetix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.memetix.chits4jexamples;

import com.memetix.chits4j.agent.Agent;
import com.memetix.chits4j.agent.AgentService;
import com.memetix.chits4j.chit.Chit;
import com.memetix.chits4j.metric.Metric;
import com.memetix.chits4j.metric.MetricService;
import java.util.Scanner;

/**
 * Hello World
 * 
 * A Command-line client for writing a couple of Metrics
 *
 * @author Jonathan Griggs <jonathan.griggs @ gmail.com>
 * @date Jul 14, 2011
 */
public class Chit4jHelloWorld 
{
    public static void main( String[] args )
    {
        MetricService service = new MetricService();
        
        String value = "";
        Scanner input=new Scanner(System.in); // Decl. & init. a Scanner.
        input.useDelimiter("\\n");
  
        System.out.print("Metric Value > ");
        while((value=input.next())!=null) {
            if(value.equalsIgnoreCase("exit")||value.equalsIgnoreCase("quit")||value.equalsIgnoreCase("q"))
                System.exit(0);
            System.out.println();  

            Metric metric = new Metric();
            metric.setAgent("Example.User@gmail.com");
            metric.setName("Clicket");
            metric.setValue(value);
            
            Metric wordCountMetric = new Metric();
            AgentService agentService = new AgentService();
            wordCountMetric.setAgent("Example.User@gmail.com");
            wordCountMetric.setName("Character Count");
            wordCountMetric.setValue("" + value.length());

            try {
                service.writeMetric(metric);
                service.writeMetric(wordCountMetric);
                System.out.println(metric.encodeAsJSON().toString());
                System.out.println(wordCountMetric.encodeAsJSON().toString());
                Agent agent = agentService.getAgent("Example.User@gmail.com");
                System.out.println("Chits for " + agent.getId());
                for(Chit chit : agent.getChits()) {
                   System.out.println("\n\t" + chit.getName()); 
                }
                //service.deleteMetric(metric);
                //service.deleteMetric(wordCountMetric);
            } catch(Exception e) {
                System.out.println("Error writing Metric\n" + e.getMessage());
            }
            System.out.print("Metric Value > ");
        }
    }
}
