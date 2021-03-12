package org.github.jrds.server.domain;


import org.github.jrds.server.Main;

import java.util.*;

public class Lesson
{

    private final String id;
    private final User educator;
    private final Set<User> learners;
    private final Map<String, Instruction> instructions = new TreeMap<>();

    public Lesson(String id, User educator, Set<User> learners)
    {
        this.id = Objects.requireNonNull(id);
        this.educator = Objects.requireNonNull(educator);
        this.learners = learners == null ? new HashSet<>() : learners;
    }

    public String getId()
    {
        return id;
    }

    public User getEducator()
    {
        return educator;
    }

    public Set<User> getLearners()
    {
        return Collections.unmodifiableSet(learners);
    }

    public void addLearner(User learner)
    { // could pass in other detail to construct user??
        learners.add(learner);
    }

    public boolean isRegisteredLearner(User learner)
    {
        return learners.contains(learner);
    }

    public boolean canConnect(User user)
    {
        return isRegisteredLearner(user) || educator.equals(user);
    }

    public Role getUserRole(User user)
    {
        if (educator.equals(user))
        {
            return Role.EDUCATOR;
        }
        else if (learners.contains(user))
        {
            return Role.LEARNER;
        }
        else
        {
            return Role.NONE;
        }
    }

    public void createInstruction(String title, String body, User author)
    {
        if (author.equals(educator))
        {
            if (!instructions.containsKey(title))
            {
                storeInstruction(new Instruction(title, body, author)); // it's already established that author and educator are equal, is there any reason to use one over the other? 
            }
            else
            {
                throw new IllegalArgumentException("This title already exists");
            }
        }
        else
        {
            throw new IllegalArgumentException("Only the educator of this lesson can add instructions");
        }
    }

    private void storeInstruction(Instruction i)
    {
        instructions.put(i.getTitle(), i);
    }

    public Instruction getInstruction(String instructionTitle)
    {
        if (instructions.containsKey(instructionTitle))
        {
            return instructions.get(instructionTitle);
        }
        else
        {
            return null;
        }
    }

    public List<Instruction> getAllInstructions()
    {
        return new ArrayList<>(instructions.values());
    }

    public void editInstructionTitle(String currentTitle, String newTitle, User u)
    {
        if (u.equals(educator))
        {
            if (instructions.containsKey(currentTitle))
            {
                instructions.put(newTitle, new Instruction(newTitle, instructions.get(currentTitle).getBody(), instructions.get(currentTitle).getAuthor()));
                instructions.remove(currentTitle);
            }
            else
            {
                System.out.println("This title already exists.");
            }
        }
        else
        {
            System.out.println("Only the educator of the class can edit the instruction.");
        }
    }


    public void editInstructionBody(String instructionTitle, String instructionBody, User u)
    {
        if (u.equals(educator))
        {
            if (instructions.containsKey(instructionTitle))
            {
                instructions.get(instructionTitle).setBody(instructionBody);
            }
            else
            {
                System.out.println("This title does not exists.");
            }
        }
        else
        {
            System.out.println("Only the educator of the class can edit the instruction.");
        }
    }


    public void removeInstruction(String instructionTitle)
    {
        instructions.remove(instructionTitle);
    }

    public void removeAllInstructions()
    {
        instructions.clear();
    }


    @Override
    public String toString()
    {
        return "Lesson [Educator Name =" + educator.getName() + ", Lesson id=" + id + ", learners=" + learners + "]";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Lesson lesson = (Lesson) o;
        return id.equals(lesson.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
