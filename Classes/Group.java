package com.myjfx.simplefx;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private List<Person> members;

    public Group(String groupName) {
        this.groupName = groupName;
        this.members = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Person> getMembers() {
        return members;
    }

    public String getMembersList() {
        StringBuilder sb = new StringBuilder();
        for (Person member : members) {
            sb.append(member.getUsername()).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "No members";
    }

    public void addMember(Person member) {
        if (!members.contains(member)) {
            members.add(member);
        }
    }
}
