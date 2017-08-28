
//饿汉模式  线程安全
public class single{

	private single(){
	}
	private static final single si = new single();
	
	public static single getSingle(){
		return si;
	}
}

//饱汉(懒汉)模式  非线程安全 ------想线程安全可以加同步保护synchronized
public class single{

	private single(){ 
	}
	private static  single si;
	
	public static single getSingle(){
		if(si == null){
			si = new single();
		}
		return si;
	}
}
//饱汉(懒汉)模式  线程安全 
public class single{

	private single(){
	}
	private static  single si;
	
	public static synchronized single getSingle(){
		if(si == null){
			si = new single();
		}
		return si;
	}
}