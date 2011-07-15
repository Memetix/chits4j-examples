package com.memetix.chits4jexamples;

import com.memetix.chits4j.metric.Metric;
import com.memetix.chits4j.metric.MetricService;

/**
 * Hello world!
 *
 */
public class Chit4jHelloWorld 
{
    public static void main( String[] args )
    {
        MetricService service = new MetricService();
        Metric metric = new Metric();
        metric.setAgent("Example Agent");
        metric.setName("Ran Example");
        metric.setValue(""+System.currentTimeMillis());
        
        try {
            service.writeMetric(metric);
            System.out.println(metric.encodeAsJSON().toString());
            service.deleteMetric(metric.getId());
        } catch(Exception e) {
            System.out.println("Error writing Metric" + e.getMessage());
        }
    }
}
