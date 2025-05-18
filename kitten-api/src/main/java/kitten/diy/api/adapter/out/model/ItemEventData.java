package kitten.diy.api.adapter.out.model;

public record ItemEventData(

        String message
) {
    public static ItemEventData CREATE = new ItemEventData("CREATE");

    public static ItemEventData REFRESH = new ItemEventData("REFRESH");

    public static ItemEventData DELETE = new ItemEventData("DELETE");
}