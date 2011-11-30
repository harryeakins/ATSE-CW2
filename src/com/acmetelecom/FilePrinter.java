package com.acmetelecom;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

class FilePrinter implements Printer {

    private static Printer instance = new FilePrinter();
	// Stream to write file
    BufferedWriter out;	
    
    private FilePrinter() {
    	try {
    		out = new BufferedWriter(new FileWriter("out.html", true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static Printer getInstance() {
        return instance;
    }
    
    public void close(){
    	try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void printHeading(String name, String phoneNumber, String pricePlan) {
    	try{
    		beginHtml();
        	out.write(h2(name + "/" + phoneNumber + " - " + "Price Plan: " + pricePlan));
        }
        catch (IOException e) {
			e.printStackTrace();
		}
        beginTable();
    }

    private void beginTable() {
    	try {
    		out.write("<table border=\"1\">");
    		out.write(tr(th("Time") + th("Number") + th("Duration") + th("Cost")));
    	}
    	 catch (IOException e) {
 			e.printStackTrace();
 		}
    }

    private void endTable() {
    	try {
    		out.write("</table>");
    	}	catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    private String h2(String text) {
        return "<h2>" + text + "</h2>";
    }

    public void printItem(String time, String callee, String duration, String cost) {
        try {
        	out.write(tr(td(time) + td(callee) + td(duration) + td(cost)));
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
    }

    private String tr(String text) {
        return "<tr>" + text + "</tr>";
    }

    private String th(String text) {
        return "<th width=\"160\">" + text + "</th>";
    }

    private String td(String text) {
        return "<td>" + text + "</td>";
    }

    public void printTotal(String total) {
    	endTable();
    	try {
    		out.write(h2("Total: " + total));
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	try {
			endHtml();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void beginHtml() throws IOException{
    	out = new BufferedWriter(new FileWriter("out.html", true));
        out.write("<html>");
        out.write("<head></head>");
        out.write("<body>");
        out.write("<h1>");
        out.write("Acme Telecom");
        out.write("</h1>");
    }

    private void endHtml() throws IOException {
        out.write("</body>");
        out.write("</html>");
        out.close();
    }
}
