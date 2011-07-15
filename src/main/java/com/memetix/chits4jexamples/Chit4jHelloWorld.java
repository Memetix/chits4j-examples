package com.memetix.chits4jexamples;

import com.memetix.chits4j.metric.Metric;
import com.memetix.chits4j.metric.MetricService;
import java.util.Scanner;

/**
 * Hello world!
 *
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
            metric.setName("Recorded Text");
            metric.setValue(value);
            
            Metric wordCountMetric = new Metric();
            wordCountMetric.setAgent("Example.User@gmail.com");
            wordCountMetric.setName("Character Count");
            wordCountMetric.setValue("" + value.length());

            try {
                service.writeMetric(metric);
                service.writeMetric(wordCountMetric);
                System.out.println(metric.encodeAsJSON().toString());
                System.out.println(wordCountMetric.encodeAsJSON().toString());
                service.deleteMetric(metric);
                service.deleteMetric(wordCountMetric);
            } catch(Exception e) {
                System.out.println("Error writing Metric" + e.getMessage());
            }
            System.out.print("Metric Value > ");
        }
    }
}
