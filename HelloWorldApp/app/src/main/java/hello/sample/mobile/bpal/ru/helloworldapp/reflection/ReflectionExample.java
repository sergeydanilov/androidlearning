package hello.sample.mobile.bpal.ru.helloworldapp.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by serg on 13.05.16.
 */
public class ReflectionExample {
    public String printReflectionExample(String[] args) {
        
        StringBuilder builder = new StringBuilder();

        // Obtain the class object if we know the name of the class
        Class rental = RentCar.class;
        try {
            // get the absolute name of the class
            String rentalClassPackage = rental.getName();
            builder.append("Class Name is: " + rentalClassPackage);
            builder.append("\n");

            // get the simple name of the class (without package info)
            String rentalClassNoPackage = rental.getSimpleName();
            builder.append("Class Name without package is: "
                    + rentalClassNoPackage);
            builder.append("\n");

            // get the package name of the class
            Package rentalPackage = rental.getPackage();
            builder.append("Package Name is: " + rentalPackage);
            builder.append("\n");

            // get all the constructors of the class
            Constructor[] constructors = rental.getConstructors();
            builder.append("Constructors are: "
                    + Arrays.toString(constructors));
            builder.append("\n");

            // get constructor with specific argument
            Constructor constructor = rental.getConstructor(Integer.TYPE);
            builder.append("\n");

            // initializing an object of the RentCar class
            RentCar rent = (RentCar) constructor.newInstance(455);


            // get all methods of the class including declared methods of
            // superclasses
            // in that case, superclass of RentCar is the class java.lang.Object
            Method[] allmethods = rental.getMethods();
            builder.append("Methods are: " + Arrays.toString(allmethods));
            builder.append("\n");
            for (Method method : allmethods) {
                builder.append("method = " + method.getName());
                builder.append("\n");
            }

            // get all methods declared in the class
            // but excludes inherited methods.
            Method[] declaredMethods = rental.getDeclaredMethods();
            builder.append("Declared Methods are: "
                    + Arrays.toString(declaredMethods));
            builder.append("\n");
            for (Method dmethod : declaredMethods) {
                builder.append("method = " + dmethod.getName());
                builder.append("\n");
            }

            // get method with specific name and parameters
            Method oneMethod = rental.getMethod("computeRentalCost",
                    new Class[] { Integer.TYPE });
            builder.append("Method is: " + oneMethod);
            builder.append("\n");

            // call computeRentalCost method with parameter int
            oneMethod.invoke(rent, 4);

            // get all the parameters of computeRentalCost
            Class[] parameterTypes = oneMethod.getParameterTypes();
            builder.append("Parameter types of computeRentalCost() are: "
                    + Arrays.toString(parameterTypes));
            builder.append("\n");

            // get the return type of computeRentalCost
            Class returnType = oneMethod.getReturnType();
            builder.append("Return type is: " + returnType);
            builder.append("\n");

            // gets all the public member fields of the class RentCar
            Field[] fields = rental.getFields();

            builder.append("Public Fields are: ");
            for (Field oneField : fields) {
                // get public field name
                Field field = rental.getField(oneField.getName());
                String fieldname = field.getName();
                builder.append("Fieldname is: " + fieldname);
                builder.append("\n");

                // get public field type
                Object fieldType = field.getType();
                builder.append("Type of field " + fieldname + " is: "
                        + fieldType);

                // get public field value
                Object value = field.get(rent);
                builder.append("Value of field " + fieldname + " is: "
                        + value);
                builder.append("\n");

            }

            // How to access private member fields of the class

            // getDeclaredField() returns the private field
            Field privateField = RentCar.class.getDeclaredField("type");

            String name = privateField.getName();
            builder.append("One private Fieldname is: " + name);
            builder.append("\n");
            // makes this private field instance accessible
            // for reflection use only, not normal code
            privateField.setAccessible(true);

            // get the value of this private field
            String fieldValue = (String) privateField.get(rent);
            builder.append("fieldValue = " + fieldValue);
            builder.append("\n");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}