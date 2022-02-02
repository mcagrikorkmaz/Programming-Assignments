import java.util.ArrayList;

class PersonalTrainer extends Person{
    private ArrayList<Member> memberArray = new ArrayList<>();
    public Member[] members;
    public String sportType;

    public PersonalTrainer(int Id, String name, String surname, String sportType){
        super(Id,name,surname);
        this.sportType = sportType;
    }

    public int returnCountofMembers(){return members.length;}

    public void addMember(Member m){
        int x = 0;
        memberArray.add(m);
        members = new Member[memberArray.size()];
        for(Member i : memberArray){
             members[x] = i;
             x++;
        }
    }

    public Member returnMember(int memberID){
        for(Member member : memberArray){
            if(member.Id == memberID) return member;
        }
        return null;
    }

    public Member ReturnFattestMember(){
        Member result = new Member(0,"","",-1,0);
        for(Member member : memberArray){
            if(member.getWeight()> result.getWeight()) result = member;
        }
        return result;
    }
}
