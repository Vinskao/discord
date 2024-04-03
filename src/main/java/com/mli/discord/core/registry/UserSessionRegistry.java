//package com.mli.discord.core.registry;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class UserSessionRegistry {
//	private static final ConcurrentHashMap<String, String> sessions = new ConcurrentHashMap<>();
//
//    public static void addUser(String sessionId, String username) {
//        sessions.put(sessionId, username);
//    }
//
//    public static void removeUser(String sessionId) {
//        sessions.remove(sessionId);
//    }
//
//    public static List<String> getAllUsernames() {
//        return new ArrayList<>(sessions.values());
//    }
//}
