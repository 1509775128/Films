/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.films.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/** 
 * MyEclipse Struts
 * Creation date: 10-25-2012
 * 
 * XDoclet definition:
 * @struts.form name="userPhotoForm"
 */
public class UserPhotoForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormFile memberPhoto;
	public FormFile getMemberPhoto() {
		return memberPhoto;
	}
	public void setMemberPhoto(FormFile memberPhoto) {
		this.memberPhoto = memberPhoto;
	}
	
	
}