class Subject {

    String name;
    String teacher;
    String room;

    public Subject(String name, String teacher, String room) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
    }
}


public class SmartTimetableGenerator {

    private Subject[] subjects;
    private String[] slots;

    private int[] assignment;

    public SmartTimetableGenerator(Subject[] subjects, String[] slots) {

        this.subjects = subjects;
        this.slots = slots;

        assignment = new int[subjects.length];

        for (int i = 0; i < assignment.length; i++) {
            assignment[i] = -1;
        }
    }


    public boolean isSafe(int subjectIndex, int slotIndex) {

        Subject current = subjects[subjectIndex];

        for (int i = 0; i < subjectIndex; i++) {

            if (assignment[i] == slotIndex) {

                Subject previous = subjects[i];

                if (current.teacher.equals(previous.teacher)) {
                    return false;
                }

                if (current.room.equals(previous.room)) {
                    return false;
                }
            }
        }

        return true;
    }


    public boolean assignSlot(int subjectIndex) {

        if (subjectIndex == subjects.length) {
            return true;
        }

        for (int slot = 0; slot < slots.length; slot++) {

            if (isSafe(subjectIndex, slot)) {

            
                assignment[subjectIndex] = slot;

                if (assignSlot(subjectIndex + 1)) {
                    return true;
                }
                assignment[subjectIndex] = -1;
            }
        }

        return false;
    }


    public void generateTimetable() {

        if (assignSlot(0)) {

            System.out.println("Valid Timetable Generated:\n");

            for (int i = 0; i < subjects.length; i++) {

                System.out.println(
                        subjects[i].name +
                        " -> " +
                        slots[assignment[i]] +
                        " | Teacher: " +
                        subjects[i].teacher +
                        " | Room: " +
                        subjects[i].room
                );
            }

        } else {

            System.out.println("No valid timetable possible.");
        }
    }


    public static void main(String[] args) {

        Subject[] subjects = {

                new Subject("Mathematics", "Dr. Sharma", "R101"),
                new Subject("Physics", "Dr. Kumar", "R102"),
                new Subject("Chemistry", "Dr. Sharma", "R103"),
                new Subject("Biology", "Dr. Mehta", "R102")
        };


        String[] slots = {

                "9:00 AM",
                "10:00 AM",
                "11:00 AM",
                "12:00 PM"
        };


        SmartTimetableGenerator generator =
                new SmartTimetableGenerator(subjects, slots);

        generator.generateTimetable();
    }
}