package com.github.fac30ff.builder.recursiveGenerics;

class Person {
    public String name;

    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends com.github.fac30ff.builder.recursiveGenerics.PersonBuilder<SELF>> {
    protected com.github.fac30ff.builder.recursiveGenerics.Person person = new com.github.fac30ff.builder.recursiveGenerics.Person();

    // critical to return SELF here
    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    protected SELF self() {
        // unchecked cast, but actually safe
        // proof: try sticking a non-PersonBuilder
        //        as SELF parameter; it won't work!
        return (SELF) this;
    }

    public com.github.fac30ff.builder.recursiveGenerics.Person build() {
        return person;
    }
}

class EmployeeBuilder
        extends com.github.fac30ff.builder.recursiveGenerics.PersonBuilder<EmployeeBuilder> {
    public EmployeeBuilder worksAs(String position) {
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

class RecursiveGenericsDemo {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder()
                .withName("Dmitri")
                .worksAs("Quantitative Analyst");
        System.out.println(eb.build());
    }
}