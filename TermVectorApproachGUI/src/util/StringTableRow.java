package util;

import java.util.ArrayList;

import processing.core.PApplet;

public class StringTableRow {
	private PApplet p;
	
	private ArrayList<String> data; 
	private int width, height;
	
	private int numCols;
	
	public StringTableRow(PApplet parent, ArrayList<String> data, int width, int height){
		this.p = parent;
		this.width = width;
		this.height = height;
	
		this.data = new ArrayList<String>(data);
		this.data.add(0, " ");
		numCols = data.size();
	}
	
	public void draw(){
		p.fill(250);
		p.stroke(20);
		p.strokeWeight(2);
		p.rect(0, 0, width, height);
		
		p.strokeWeight(1);
		float colWidth = width/(float)numCols;
		
		float prev = -colWidth;
		for(int i = 0; i < numCols+1; i++){
			float next = colWidth*FloatingStringTable.colWidthsWeight[i];
			p.line(prev+next, 0, prev + next, height);
			prev += next;
		}
		
		p.textAlign(PApplet.LEFT, PApplet.CENTER);
		p.fill(20);
		p.textSize(13);
		float prevIndent = 10-colWidth;
		for(int i = 0; i < numCols+1; i++){
			float nextIndent = colWidth*FloatingStringTable.colWidthsWeight[i];
			p.text(data.get(i),prevIndent + nextIndent, height * 0.5f);
			prevIndent += nextIndent;
		}
	}

	
}