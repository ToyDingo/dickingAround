package org.clgd;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.beam.vendor.grpc.v1p60p1.com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;


public class Main{

    @Getter
    @Setter
    class PhoneNumber {
        @SerializedName("number")
        private long number;
    }

    @Getter
    @Setter
    class Person {
        private String name;
        private int age;
        @SerializedName("phoneNumber")
        private PhoneNumber phoneNumber;
    }

    @Getter
    @Setter
    static class Persons {
        @SerializedName("persons")
        private List<Person> persons;
    }

    public static void main(String[] args) {
        // Define the JSON string
        String jsonString = "{\"persons\":[{\"person\":{\"name\":\"kevin\",\"age\":39,\"phoneNumber\":{\"number\":7703633175}}},{\"person\":{\"name\":\"jing\",\"age\":39,\"phoneNumber\":{\"number\":4045430694}}}]}";
        String filePath = "/Users/kevin.ford/Downloads/test(1).json";

        // Create a Gson instance
        Gson gson = new Gson();

        // Parse the JSON string into a PersonsList object
        try {
            Reader reader = new FileReader(filePath);
            Persons persons = gson.fromJson(reader, Persons.class);

            for (Person person : persons.getPersons()) {
                System.out.println("Name: " + person.getName());
                System.out.println("Age: " + person.getAge());
                //System.out.println("Phone Number: " + person.getPhoneNumber().getNumber());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Access the data
//        for (Person person : persons.getPersons()) {
//            System.out.println("Name: " + person.getName());
//            System.out.println("Age: " + person.getAge());
//            System.out.println("Phone Number: " + person.getPhoneNumber().getNumber());
//            System.out.println();
//        }
    }



//    public static void main(String[] args) {
//        Pipeline pipeline = Pipeline.create(PipelineOptionsFactory.create());
//        List<Integer> inputList = List.of(1,2,3,4,5);
//        PCollection<Integer> input = pipeline.apply(Create.of(inputList));
//        PCollection<Integer> output = input.apply(ParDo.of(new ProcessElementFn()));
//        output.apply(ParDo.of(new PrintElementFn()));
//        pipeline.run().waitUntilFinish();
//    }
//
//    static class ProcessElementFn extends DoFn<Integer, Integer> {
//        @ProcessElement
//        public void processElement(ProcessContext c){
//            Integer element = c.element();
//            Integer processedElement = element * 2;
//            System.out.println(processedElement);
//            c.output(processedElement);
//        }
//    }
//
//    static class PrintElementFn extends DoFn<Integer, Integer> {
//        @ProcessElement
//        public void processElement(ProcessContext c){
//            Integer element = c.element();
//            System.out.println(element);
//        }
//    }
}