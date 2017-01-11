package com.digital.ayaz.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ats on 11/01/17.
 */
public class TrailerModel {
    public List<Trailer> results;

    public static class Trailer implements Parcelable {

        public static final Parcelable.Creator<Trailer> CREATOR
                = new Parcelable.Creator<Trailer>() {
            public Trailer createFromParcel(Parcel in) {
                return new Trailer(in);
            }

            public Trailer[] newArray(int size) {
                return new Trailer[size];
            }
        };

        public String key;

        public String name;

        public Trailer() {
        }

        private Trailer(Parcel in) {
            key = in.readString();
            name = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeString(key);
            out.writeString(name);
        }

        /**
         * Helper method to build youtube link.
         */
        public String getYoutubeLink() {
            return "https://www.youtube.com/watch?v=" + this.key;
        }
    }
}
