import model.Resume;

import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws Exception {
        Resume r = new Resume("resume from reflection");
        Method[] methods = r.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("toString")) System.out.println(method.invoke(r));
        }
    }
}
