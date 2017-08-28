package com.example.ipcTest;

import android.os.Parcel;
import android.os.Parcelable;


//parcelabel 相较与Serialzable  速度快，较少产生垃圾内存，但接口实现复杂、无法持久化
public class person implements Parcelable {

	public person() {
		// TODO Auto-generated constructor stub
	}
	
	public int id;
	public float f;
	public byte[]  bs = new byte[20];
	public String name = "";
/*
	public int add(){
		return id+100;
	}
	public int add2(int a, int b){
		return a+b;
	}
*/	
	public static final Parcelable.Creator<person> CREATOR = new Creator<person>() {
		
		@Override
		public person[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return new person[arg0];
		}
		
		@Override
		public person createFromParcel(Parcel arg0) {
			// TODO Auto-generated method stub
			person per = new person();
			per.id = arg0.readInt();
			per.f = arg0.readFloat();
			arg0.readByteArray(per.bs);
			per.name = arg0.readString();
			return per;
		}
	};
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int arg1) {
		// TODO Auto-generated method stub
		out.writeInt(id);
		out.writeFloat(f);
		out.writeByteArray(bs);
		out.writeString(name);
	//	out.writeInt(add());
	
	}
}
