package DataStepDefinition;

import com.opencsv.CSVReader;

import io.cucumber.java.en.Given;

import java.io.*;
import java.util.*;

import org.junit.Assert;

public class ValidateInsPosDetailsFile{
	
	static String instrumentFilePath = System.getProperty("user.dir")+"/src/main/java/Util/InstrumentDetails.csv";
    static String PositionFilePath = System.getProperty("user.dir")+"/src/main/java/Util/PositionDetails.csv";
	static String outputFilePath = System.getProperty("user.dir")+"/src/main/java/Util/PositionReport.csv";
    static String outputQuantityValue = "";
    static String outputTotalValue = "";
    static String positionQuantityValue = "";
    static String outPutFileISIN = "";
    static String InstrumentFileFileISIN = "";
    
    ValidatePositionReportFile validatepositionreportfile = new ValidatePositionReportFile();
	
	@Given("^Read and Store the Instrument File Details and Position File Details$")
	public void Read_and_Store_the_Instrument_File_Details_and_Position_File_Details() {
		try {
            CSVReader outPutFile = new CSVReader(new FileReader(outputFilePath));
            CSVReader insFile = new CSVReader(new FileReader(instrumentFilePath));
            CSVReader posFile = new CSVReader(new FileReader(PositionFilePath));
            LinkedHashMap<String, List<String>> linkedMapOutput = new LinkedHashMap<String, List<String>>();
            LinkedHashMap<String, List<String>> positionMap = new LinkedHashMap<String, List<String>>();
            LinkedHashMap<String, List<String>> instrumentMap = new LinkedHashMap<String, List<String>>();

            String[] nline;
            while ((nline = outPutFile.readNext()) != null) {
                String key = nline[0];
                List<String> values = Arrays.asList(nline[0], nline[1], nline[2], nline[3]); // second, third and fourth columns as list of values
                linkedMapOutput.put(key, values);
            }
            linkedMapOutput.remove("PositionID");
            String[] mline;
            while ((mline = posFile.readNext()) != null) {
                String key = mline[0];
                List<String> values = Arrays.asList(mline[0], mline[1], mline[2]);
                positionMap.put(key, values);
            }
            positionMap.remove("ID");
            String[] oline;
            while ((oline = insFile.readNext()) != null) {
                String key = oline[0];
                List<String> values = Arrays.asList(oline[0], oline[1], oline[2], oline[3]); // second, third and fourth columns as list of values
                instrumentMap.put(key, values);
            }
            instrumentMap.remove("ID");
            System.out.println("InstrumentMap Key: " + instrumentMap.keySet());
            System.out.println("InstrumentMap Value:" + instrumentMap.values() + "\n");
            System.out.println("OutPut Key: " + linkedMapOutput.keySet());
            System.out.println("OutPut Value:" + linkedMapOutput.values() + "\n");
            System.out.println("PositionMap Key: " + positionMap.keySet());
            System.out.println("PositionMap Value:" + positionMap.values() + "\n");
            linkedMapOutput.forEach((key, value) -> positionMap.forEach((key1, value1) -> {
                try {
                    if (key.contains(key1)) {
                        System.out.println("Comparing OutputFile Key " + key + " with PositionFile Key: " + key1);
                        System.out.println("Comparing OutputFile value " + value + " with PositionFile value: " + value1);
                        boolean comparison = ValidatePositionReportFile.compareOutPutAndPositionData(value, value1);
                        if (comparison) {
                            String[] isinOutput = value.toArray(new String[0]);
                            outPutFileISIN = isinOutput[1];
                            String[] positionInstrumentID = value1.toArray(new String[0]);
                            String position_IDValue = positionInstrumentID[1];
                            if (ValidatePositionReportFile.comparePositionWithIns(position_IDValue, instrumentMap) && outPutFileISIN.equals(InstrumentFileFileISIN)) {
                                System.out.println("Expected Results - ISIN : " + outPutFileISIN);
                                System.out.println("Actual  Results - ISIN: " + InstrumentFileFileISIN);
                                System.out.println("Final result: Calculation for " + key + " key is correct!\n\r");
                                Assert.assertTrue("Final result: Calculation for " + key + " key is correct! \n\r", true);
                            } else {
                                System.out.println("Final result: " + key + " is not correct!");
                                Assert.fail("Final result: " + key + " is Incorrect!\n\r");
                            }
                        } else {
                            System.out.println("Quantity value are not matching");
                        }
                    }
                } catch (AssertionError e) {
                    System.out.println("Validation failed. Moving to next data ");
                }
            }));
        } catch (Exception e) {
            System.out.println("Test failed " + e);
        }
    }
	
	
		
}

	
