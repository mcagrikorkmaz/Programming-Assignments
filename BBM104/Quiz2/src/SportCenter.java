import java.util.ArrayList;

class SportCenter {
    private ArrayList<PersonalTrainer> trainerArray = new ArrayList<>();
    public String name;
    public PersonalTrainer[] PTs;

    public SportCenter(String name){
        this.name = name;
    }

    public void addPT(PersonalTrainer pt){
        int x = 0;
        trainerArray.add(pt);
        PTs = new PersonalTrainer[trainerArray.size()];
        for(PersonalTrainer i : trainerArray){
            PTs[x] = i;
            x++;
        }
    }

    public PersonalTrainer searchPT(String name, String surname){
        for(PersonalTrainer trainer : trainerArray){
            if(trainer.name.equals(name)&&trainer.surname.equals(surname)) return trainer;
        }
        return null;
    }
}
