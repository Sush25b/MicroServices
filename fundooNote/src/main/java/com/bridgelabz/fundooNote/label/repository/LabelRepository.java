package com.bridgelabz.fundooNote.label.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.label.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> 
{
	
}
