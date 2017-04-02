package com.sevenj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="toolplancomment")
public class Comment {
	private int ID;
	private ToolPlan toolPlan;
	private String comment;
	private String commentUser;
	private Date commentDate;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@ManyToOne
	@JoinColumn(name="ToolPlans", nullable=false)
	public ToolPlan getToolPlan() {
		return toolPlan;
	}

	public void setToolPlan(ToolPlan toolPlan) {
		this.toolPlan = toolPlan;
	}

	@Column(name="Content", nullable= false)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name="CommentUser", nullable= false)
	public String getCommentUser() {
		return commentUser;
	}

	
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

	@Column(name="CommentDate", nullable= false)
	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
