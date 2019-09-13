public class UserData {

    Data DataObject;
    public Data getData() {
        return DataObject;
    }

    public void setData(Data dataObject) {
        this.DataObject = dataObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(DataObject, userData.DataObject)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(DataObject)
                .toHashCode();
    }

    public class Data {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Data data = (Data) o;

            return new org.apache.commons.lang3.builder.EqualsBuilder()
                    .append(getId(), data.getId())
                    .append(getEmail(), data.getEmail())
                    .append(getFirst_name(), data.getFirst_name())
                    .append(getLast_name(), data.getLast_name())
                    .append(getAvatar(), data.getAvatar())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                    .append(getId())
                    .append(getEmail())
                    .append(getFirst_name())
                    .append(getLast_name())
                    .append(getAvatar())
                    .toHashCode();
        }
    }

}
