package com.coderulez.senai.leansurvey.model;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Employee generated by hbm2java
 */

public class Employee implements Parcelable
{
	private Long id;
	private String name;
	private String username;
	private String password;
	private String salt;

	public Employee()
	{

	}

	protected Employee(Parcel in)
	{
		id = in.readLong();
		name = in.readString();
		username = in.readString();
		password = in.readString();
		salt = in.readString();
	}

	public static final Creator<Employee> CREATOR = new Creator<Employee>() {
		@Override
		public Employee createFromParcel(Parcel in) {
			return new Employee(in);
		}

		@Override
		public Employee[] newArray(int size) {
			return new Employee[size];
		}
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public int describeContents() {
		return 5;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(username);
		dest.writeString(password);
		dest.writeString(salt);
	}
}
