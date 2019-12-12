package com.virugan.context;

public class myLogger {

    private final static  String infohead="info >>>> ";
    private final static  String debughead="debug >>>> ";

    public static void info(String paramNme,Object paramVal){
        System.out.println(infohead+paramNme +" :["+paramVal+"]");
    }

    public static void info(Object paramVal){
        System.out.println(infohead+paramVal.getClass().getSimpleName() +" :["+paramVal+"]");
    }

    public static void info(Object[] paramVal){
        StringBuffer buffer = new StringBuffer();
        buffer.append(infohead +" :[");
        int length = paramVal.length;
        int i=0;
        for(Object obj:paramVal){
            if(obj!=null){
                buffer.append(obj.toString());
            }else{
                buffer.append(obj);
            }
            if(i<length-1){
                buffer.append(",");
            }
            i=i+1;
        }
        buffer.append("]");
        System.out.println(buffer.toString());
    }

    public static void info(String paramNme,Object[] paramVal){
        StringBuffer buffer = new StringBuffer();
        buffer.append(infohead+paramNme +" :[");
        int length = paramVal.length;
        int i=0;
        for(Object obj:paramVal){
            if(obj!=null){
                buffer.append(obj.toString());
            }else{
                buffer.append(obj);
            }
            if(i<length-1){
                buffer.append(",");
            }
            i=i+1;
        }
        buffer.append("]");
        System.out.println(buffer.toString());
    }

    public static void debug(String paramNme,Object paramVal){
        System.out.println(infohead+paramNme +" :["+paramVal+"]");
    }

    public static void debug(Object paramVal){
        System.out.println(infohead+paramVal.getClass().getSimpleName() +" :["+paramVal+"]");
    }

    public static void debug(Object[] paramVal){
        StringBuffer buffer = new StringBuffer();
        buffer.append(infohead +" :[");
        int length = paramVal.length;
        int i=0;
        for(Object obj:paramVal){
            if(obj!=null){
                buffer.append(obj.toString());
            }else{
                buffer.append(obj);
            }
            if(i<length-1){
                buffer.append(",");
            }
            i=i+1;
        }
        buffer.append("]");
        System.out.println(buffer.toString());
    }

    public static void debug(String paramNme,Object[] paramVal){
        StringBuffer buffer = new StringBuffer();
        buffer.append(infohead+paramNme +" :[");
        int length = paramVal.length;
        int i=0;
        for(Object obj:paramVal){
            if(obj!=null){
                buffer.append(obj.toString());
            }else{
                buffer.append(obj);
            }
            if(i<length-1){
                buffer.append(",");
            }
            i=i+1;
        }
        buffer.append("]");
        System.out.println(buffer.toString());
    }
}
