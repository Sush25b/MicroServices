package com.bridgelabz.fundooNote.label.dto;

import javax.persistence.Column;

public class LabelDto 
{
	private String labelTitle;

	public String getLabelTitle() {
		return labelTitle;
	}

	public void setLabelTitle(String labelTitle) {
		this.labelTitle = labelTitle;
	}

	@Override
	public String toString() {
		return "LabelDto [labelTitle=" + labelTitle + "]";
	}
	
}
