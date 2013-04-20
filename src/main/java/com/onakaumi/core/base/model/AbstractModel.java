package com.onakaumi.core.base.model;

import java.io.Serializable;

public abstract class AbstractModel<ID extends Serializable> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	public abstract ID getId();

	public abstract void setId(ID id);

	@Override
	public String toString() {
		return "This model is: " + getClass().getSimpleName() + "[id = "
				+ getId() + "]";
	}

}
