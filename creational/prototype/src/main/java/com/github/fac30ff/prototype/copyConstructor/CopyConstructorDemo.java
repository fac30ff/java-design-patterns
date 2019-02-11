package com.github.fac30ff.prototype.copyConstructor;

class Address {
    public String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address(com.github.fac30ff.prototype.copyConstructor.Address other) {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee {
    public String name;
    public com.github.fac30ff.prototype.copyConstructor.Address address;

    public Employee(String name, com.github.fac30ff.prototype.copyConstructor.Address address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other) {
        name = other.name;
        address = new com.github.fac30ff.prototype.copyConstructor.Address(other.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class CopyConstructorDemo {
    public static void main(String[] args) {
        Employee john = new Employee("John",
                new com.github.fac30ff.prototype.copyConstructor.Address("123 London Road", "London", "UK"));

        //Employee chris = john;
        Employee chris = new Employee(john);

        chris.name = "Chris";
        System.out.println(john);
        System.out.println(chris);
    }
}
