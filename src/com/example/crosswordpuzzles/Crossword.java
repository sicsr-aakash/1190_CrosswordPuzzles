package com.example.crosswordpuzzles;

public class Crossword {

	private int crossword_no;
	private String letter;
	private int clue_no;
	
	public Crossword() {
	}

	public Crossword(String letter,int clue_no){
		super();
		this.letter=letter;
		this.clue_no=clue_no;
	}
	
	public void setNo(int crossword_no){
		this.crossword_no=crossword_no;
	}
	
	public int getNo(){
		return this.crossword_no;
	}
	
	public void setLetter(String letter){
		this.letter=letter;
	}
	
	public String getLetter(){
		return this.letter;
	}
	
	public void setPos(int clue_no){
		this.clue_no=clue_no;
	}
	
	public int getClueno(){
		return this.clue_no;
	}
}
