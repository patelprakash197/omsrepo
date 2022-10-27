package bdd.E2E_API_Framework;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import resources.DataDriven;

public class GetExceldata 
{
	@Test
    public void main() throws IOException
    {
    	DataDriven d = new DataDriven();
    	ArrayList<String> data = d.getData("AddProfile");
    	
    	System.out.print(data.get(0) +" | ");
    	System.out.print(data.get(1) +" | ");
    	System.out.print(data.get(2) +" | ");
    	System.out.print(data.get(3) +" | ");
    }
}
