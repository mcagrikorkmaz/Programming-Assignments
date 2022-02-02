class Member extends Person{
    private double weight;
    private double height;

    public Member(int Id,String name, String surname, double weight, double height){
        super(Id,name,surname);
        this.weight = weight;
        this.height = height;
    }

    private double bmi(){
        return weight/Math.pow(height,2);
    }

    public String weightStatus(){
        if(bmi()<18.5){return "Thin";}
        else if(18.5<=bmi()&&bmi()<=24.9){return "Normal";}
        else if(25<=bmi()&&bmi()<=29.9){return "Fat";}
        else{return "Obese";}
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
