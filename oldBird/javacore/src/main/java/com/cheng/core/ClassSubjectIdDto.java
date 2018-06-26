package com.cheng.core;

import java.util.ArrayList;
import java.util.List;

public class ClassSubjectIdDto {
	private Long classId;
	private Long subjectId;
	public ClassSubjectIdDto(){};
	public ClassSubjectIdDto(Long classId,Long subjectId){
		this.classId=classId;
		this.subjectId=subjectId;
	}
	
	  public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	@Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }

	    ClassSubjectIdDto classSubjectIdDto = (ClassSubjectIdDto) o;

	    if (!classId.equals(classSubjectIdDto.classId)) {
	      return false;
	    }
	    return subjectId.equals(classSubjectIdDto.subjectId);

	  }

	  @Override
	  public int hashCode() {
	    int result = classId.hashCode();
	    result = 31 * result + subjectId.hashCode();
	    return result;
	  }
	public boolean isAllPropZero(){
		return 0L==this.classId&&0L==this.subjectId;
	}
	
	public static void main(String[] args) {
		long a=10000311201411003L;
		long b=1000031120141003L;
		System.out.println(a==b);
	}
}

