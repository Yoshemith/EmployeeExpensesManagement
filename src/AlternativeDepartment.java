public record AlternativeDepartment(
        String name, String managerName
) {
    //Here goes only static methods or properties
    //No regular methods in here
    //The RECORD classes are designed to be lightweight classes, purely to model data,
    //where you're going to set all the properties in the constructor
    //and you're going to have get methods for everything and no other logic or
    //no other real methods at all.
}
