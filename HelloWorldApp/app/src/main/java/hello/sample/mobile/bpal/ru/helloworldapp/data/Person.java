package hello.sample.mobile.bpal.ru.helloworldapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class Person implements Parcelable {
    private String id;
    private String name;
    private String grade;

    // Constructor
    public Person(String id, String name, String grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }


    // Parcelling part
    public Person(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.id = data[0];
        this.name = data[1];
        this.grade = data[2];
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.id,
                this.name,
                this.grade});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
