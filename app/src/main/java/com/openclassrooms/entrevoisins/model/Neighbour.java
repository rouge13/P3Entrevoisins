package com.openclassrooms.entrevoisins.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */

public class Neighbour implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    /** Identifier */

    private long id;

    public Neighbour(long id, String name, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.isFavorite = isFavorite;
    }

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Adress */
    private String address;

    /** Phone number */
    private String phoneNumber;

    /** About me */
    private String aboutMe;

    /** Is added favorite true or false */
    private boolean isFavorite;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     */
    public Neighbour(long id, String name, String avatarUrl, String address,
                     String phoneNumber, String aboutMe, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.aboutMe = aboutMe;
        this.isFavorite = isFavorite;
    }

    public Neighbour(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.avatarUrl = in.readString();
        this.address = in.readString();
        this.phoneNumber = in.readString();
        this.aboutMe = in.readString();
        isFavorite = in.readByte() != 0;
    }

    public Neighbour(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public boolean getIsFavorite() {return isFavorite; }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.address);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.aboutMe);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

//    @Override
//    public String toString() {
//        return "Neighbour{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", avatarUrl='" + avatarUrl + '\'' +
//                ", address='" + address + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", aboutMe='" + aboutMe + '\'' +
//                ", isFavorite=" + isFavorite +
//                '}';
//    }

}
