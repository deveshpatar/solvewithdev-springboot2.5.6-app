package com.solvewithdev.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.solvewithdev.entity.Property;

/**
 * 
 * @author devesh
 *
 */
@Service
public class PropertyCSVProcessor {

    public List<Property> getPropertyListFromCSV(){
    	List<Property> properties =  new ArrayList<Property>();
    	Property property;
    	
    	String csvFile = "D:\\DEVTOOLS\\Workspace\\POC\\hack2hire_21\\eligible_properties_10_15_20.csv";
        String line = "";
        int iteration = 0;

        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	

            while ((line = br.readLine()) != null) {
            	property = new Property();
            	//skip first row from csv
            	if(iteration == 0) {
                    iteration++;  
                    continue;
                }

                // use comma as separator
                String[] propertyArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				property.setPropertyId(Long.valueOf(propertyArray[0]));
				//System.out.println("Property Id = " + property.getPropertyId());
                property.setPropertyName(propertyArray[1]);
                //System.out.println("Property Name = " + property.getPropertyName());
                property.setPropertyAdd1(propertyArray[2].substring(1, propertyArray[2].length()-1));
                property.setPropertyAdd2(propertyArray[3].substring(1, propertyArray[3].length()-1));
                property.setPropertyPrice(propertyArray[4]);
                property.setPropertyareaMeasurement(propertyArray[5]);
                property.setPropertyType(propertyArray[6]);
                
                //System.out.println("------------");
                
                properties.add(property);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	
    	
    	return properties;
    	
    }
    
}