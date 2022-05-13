package QueryHandlers;

public interface QueryHandler<Entity>
{
    boolean IsQueryMatch(Entity t);
}