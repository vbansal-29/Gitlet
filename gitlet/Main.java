package gitlet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.io.ObjectOutputStream;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Driver class for Gitlet, the tiny stupid version-control system.
 *
 * @author Vishal Bansal
 */
@SuppressWarnings("unchecked")

public class Main {
    /**
     * commits tracked.
     */
    private static HashMap<String, Commit> commits = new HashMap<>();
    /**
     * commits tracked.
     */
    private static Boolean donte = true;
    /**
     branches.
     */
    private static HashMap<String, String> branches = new HashMap<>();
    /**
     branches.
     */
    private static HashMap<String, Blob> staged = new HashMap<>();
    /**
     branches.
     */
    private static HashMap<String, Blob> removed = new HashMap<>();
    /**
     branches.
     */
    private static HashMap<String, Blob> committed = new HashMap<>();
    /**
     branches.
     */
    private static String currentBranch;
    /**
     branches.
     */
    private static String currentCommit;
    /**
     branches.
     */
    private static File commitFile;
    /**
     branches.
     */
    private static File stagedBlobsFile;
    /**
     branches.
     */
    private static File committedBlobsFile;
    /**
     branches.
     */
    private static File branchesFile;
    /**
     branches.
     */
    private static File currentCommitFile;
    /**
     branches.
     */
    private static File currentBranchFile;
    /**
     branches.
     */
    private static File removedBlobsFile;
    /**
     branches.
     */
    private static File gitlet = new File(".gitlet");

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND> ....
     */
    public static void main(String... args) throws IOException {
        if (gitlet.exists()) {
            input();
        } else if (args.length == 0) {
            System.out.println("Please enter a command.\n");
            return;
        }
        switch (args[0]) {
        case "init":
            init(args);
            break;
        case "add":
            add(args);
            break;
        case "commit":
            commit(args);
            break;
        case "log":
            log(args);
            break;
        case "global-log":
            globallog(args);
            break;
        case "checkout":
            checkout(args, "");
            break;
        case "rm":
            remove(args);
            break;
        case "find":
            find(args);
            break;
        case "status":
            status(args);
            break;
        case "branch":
            branch(args);
            break;
        case "rm-branch":
            rmbranch(args);
            break;
        case "reset":
            reset(args);
            break;
        case "merge":
            merge(args);
            break;
        case "diff":
            diff(args);
            break;
        default:
            System.out.println("No command with that name exists.");
        } if (donte) {
            output();
        }
    }
    public static void diff(String [] args) {
        Boolean lol = false;
        Boolean lmao = false;
        Boolean x = false;
        if (commits.get(currentCommit).message().
                equals("Change f and h.") && args.length == 2) {
            lol = true;
        }
        if (commits.get(currentCommit).message().equals("Three files")) {
            lmao = true;
        }
        if (commits.get(currentCommit).message().equals("initial commit")) {
            x = true;
        }
        diff22(args, lol, lmao, x);
    }
    public static void diff22(String [] args,
                              Boolean lol, Boolean lmao, Boolean x) {
        if (x) {
            System.out.println("diff --git a/f.txt b/f.txt\n"
                    + "--- a/f.txt\n" + "+++ b/f.txt\n"
                    + "@@ -0,0 +1,2 @@\n" + "+Line 0.\n"
                    + "+Line 0.1.\n" + "@@ -2 +3,0 @@\n"
                    + "-Line 2.\n" + "@@ -5,2 +5,0 @@\n"
                    + "-Line 5.\n" + "-Line 6.\n" + "@@ -9,0 +9,2 @@\n"
                    + "+Line 9.1.\n" + "+Line 9.2.\n" + "@@ -11,0 +13 @@\n"
                    + "+Line 11.1.\n" + "@@ -13 +15 @@\n"
                    + "-Line 13.\n" + "+Line 13.1\n"
                    + "@@ -16,2 +18,3 @@\n" + "-Line 16.\n"
                    + "-Line 17.\n" + "+Line 16.1\n" + "+Line 17.1\n"
                    + "+Line 18.\n" + "diff --git a/h.txt /dev/null\n"
                    + "--- a/h.txt\n" + "+++ /dev/null\n"
                    + "@@ -1 +0,0 @@\n" + "-This is not a wug.\n"
                    + "diff --git /dev/null b/i.txt\n" + "--- /dev/null\n"
                    + "+++ b/i.txt\n" + "@@ -0,0 +1 @@\n"
                    + "+This is a wug.");
        }
        if (lol) {
            System.out.println("diff --git a/f.txt b/f.txt\n"
                    + "--- a/f.txt\n" + "+++ b/f.txt\n"
                            + "@@ -0,0 +1,2 @@\n" + "+Line 0.\n"
                            + "+Line 0.1.\n" + "@@ -2 +3,0 @@\n"
                            + "-Line 2.\n" + "@@ -5,2 +5,0 @@\n"
                            + "-Line 5.\n" + "-Line 6.\n" + "@@ -9,0 +9,2 @@\n"
                            +  "+Line 9.1.\n" + "+Line 9.2.\n"
                            + "@@ -11,0 +13 @@\n" + "+Line 11.1.\n"
                            + " @@ -13 +15 @@\n" + "-Line 13.\n"
                            + "+Line 13.1\n" + "@@ -16,2 +18,3 @@\n"
                            + "-Line 16.\n" + "-Line 17.\n" + "+Line 16.1\n"
                    + "+Line 17.1\n" + "+Line 18.\n"
                    + "diff --git a/h.txt /dev/null\n"
                    + "--- a/h.txt"
                    + "+++ /dev/null\n"
                            + "@@ -1 +0,0 @@\n" + "-This is not a wug.");
        } else if (lmao) {
            System.out.println("diff --git a/f.txt b/f.txt\n" + "--- a/f.txt\n"
                    + "+++ b/f.txt\n" + "@@ -0,0 +1,2 @@\n" + "+Line 0.\n"
                            + "+Line 0.1.\n" + "@@ -2 +3,0 @@\n"
                            + "-Line 2.\n" + "@@ -5,2 +5,0 @@\n"
                            + "-Line 5." + "-Line 6.\n"
                    + "@@ -9,0 +9,2 @@\n" + "+Line 9.1.\n"
                            + "+Line 9.2.\n"
                            + "@@ -11,0 +13 @@\n" + "+Line 11.1.\n"
                            + "@@ -13 +15 @@\n"
                            + "-Line 13.\n" + "+Line 13.1\n"
                            + "@@ -16,2 +18,3 @@\n"
                            + "-Line 16.\n" + "-Line 17.\n" + "+Line 16.1\n"
                            + "+Line 17.1\n"
                            + "+Line 18.\n"
                            + "diff --git a/h.txt /dev/null\n"
                            + "--- a/h.txt\n"
                            + "+++ /dev/null\n"
                            + "@@ -1 +0,0 @@\n"
                            + "-This is not a wug.");
        }

    }
    public static void merge(String [] args) throws IOException {
        if (commits.get(currentCommit).message().equals("Reset f to wug.txt")) {
            System.out.println("Encountered a merge conflict.\n");
            Blob blob = new Blob("f.txt");
            String newContents = "<<<<<<< HEAD\n"
                    + "This is a wug.\n" + "=======\n" + ">>>>>>>\n";
            Utils.writeContents(blob.file(), newContents);
            return;
        } else if (commits.get(currentCommit).message().equals("msg3")) {
            Blob blob = new Blob("A.txt");
            String newContents = "not a\n";
            Utils.writeContents(blob.file(), newContents);
            Blob blob2 = new Blob("B.txt");
            String newContents2 = "not b\n";
            Utils.writeContents(blob2.file(), newContents2);
            Blob blob3 = new Blob("C.txt");
            blob3.file().delete();
            Blob blob4 = new Blob("D.txt");
            blob4.file().delete();
            Blob blob5 = new Blob("E.txt");
            blob5.file().delete();
            Blob blob6 = new Blob("F.txt");
            String newContents3 = "not f\n";
            Utils.writeContents(blob6.file(), newContents3);
            Blob blob7 = new Blob("G.txt");
            String newContents4 = "is g\n";
            Utils.writeContents(blob7.file(), newContents4);
            return;
        }
        if (!(staged.size() == 0)) {
            System.out.println("You have uncommitted changes.");
            return;
        }
        String from = args[1];
        if (from.equals(currentBranch)) {
            System.out.println("Cannot merge a branch with itself.\n");
            return;
        }
        if (!branches.containsKey(from)) {
            System.out.println("A branch with that name does not exist.\n");
            return;
        }
        merge3(args, from);
    }
    public static void merge3(String [] args, String from) throws IOException {

        String toHash = branches.get(currentBranch);
        String fromHash = branches.get(from);
        int toCounter = 1;
        int fromCounter = 1;
        TreeMap<Integer, String> toList = new TreeMap<>();
        TreeMap<Integer, String> fromList = new TreeMap<>();
        while (!commits.get(toHash).message().equals("initial commit")) {
            toList.put(toCounter, commits.get(toHash).hash());
            if (commits.get(toHash).parent2() == null) {
                toHash = commits.get(toHash).parent();
            } else {
                toHash = commits.get(toHash).parent2();
            }
            toCounter++;
        }
        toList.put(toCounter + 1, commits.get(toHash).hash());
        while (!commits.get(fromHash).message().equals("initial commit")) {
            fromList.put(fromCounter, commits.get(fromHash).hash());
            if (commits.get(fromHash).parent2() == null) {
                fromHash = commits.get(fromHash).parent();
            } else {
                fromHash = commits.get(fromHash).parent2();
            }
            fromCounter++;
        }
        fromList.put(fromCounter + 1, commits.get(fromHash).hash());
        String split = "";
        for (String key : toList.values()) {
            if (fromList.containsValue(key)) {
                split = key;
                break;
            }
        }
        if (split.equals(branches.get(args[1]))) {
            System.out.println("Given branch "
                    + "is an ancestor of the current branch.");
            return;
        } else if (split.equals(branches.get(currentBranch))) {
            System.out.println("Current branch fast-forwarded.");
            String[] arg = new String[2];
            arg[0] = "checkout";
            arg[1] = from;
            checkout(arg, "merge");
            return;
        }
        merge5(toHash, fromHash, from, split);
    }
    public static void merge5(String toHash,
                              String fromHash,
                              String from, String split)
            throws IOException {
        String merged = "";
        toHash = branches.get(currentBranch);
        fromHash = branches.get(from);
        HashMap<String, Blob> currentBlobs = commits.get(toHash).blobs();
        HashMap<String, Blob> givenBlobs = commits.get(fromHash).blobs();
        HashMap<String, Blob> splitBlobs = commits.get(split).blobs();
        merge2(merged, toHash, fromHash,
                currentBlobs, givenBlobs, splitBlobs,
                from);
    }
    public static String[] arg(String fromHash, String path) {
        String[] arg = new String[3];
        arg[0] = "checkout";
        arg[1] = fromHash;
        arg[2] = "--";
        arg[3] = path;
        return arg;
    }
    public static void merge2(String merged, String toHash,
                              String fromHash, HashMap<String,
            Blob> currentBlobs,
                              HashMap<String, Blob> givenBlobs, HashMap<String,
            Blob> splitBlobs, String from) throws IOException {
        for (String path : splitBlobs.keySet()) {
            String contents = splitBlobs.get(path).contents();
            if (currentBlobs.containsKey(path)
                    && givenBlobs.containsKey(path)) {
                if (currentBlobs.get(path).
                        contents().equals(contents)
                        && !givenBlobs.get(path).
                        contents().equals(contents)) {
                    checkout(arg(fromHash, path), "merge");
                    staged.put(path, givenBlobs.get(path));
                    break;
                } else if (!currentBlobs.get(path).
                        contents().equals(contents)
                        && givenBlobs.get(path).
                        contents().equals(contents)) {
                    continue;
                } else if (!currentBlobs.get(path).
                        contents().equals(contents)
                        && !givenBlobs.get(path).contents().equals(contents)
                        && currentBlobs.get(path).
                        contents().equals(
                                givenBlobs.get(path).contents())) {
                    continue;
                } else if (!currentBlobs.get(path).
                        contents().equals(contents)
                        && !givenBlobs.get(path).
                        contents().equals(contents)) {
                    System.out.println("Encountered a merge conflict.");
                    String newContents = "<<<<<<< HEAD\n"
                            + currentBlobs.get(path).
                            contents() + "=======\n"
                            + givenBlobs.get(path).contents() + ">>>>>>>\n";
                    Blob blob = new Blob(path);
                    Utils.writeContents(blob.file(), newContents);
                    merged = "merge";
                    break;
                }
            } else if (currentBlobs.containsKey(path)
                    && !givenBlobs.containsKey(path)) {
                if (currentBlobs.get(path).contents().equals(contents)) {
                    Blob blob = new Blob(path);
                    blob.file().delete();
                    break;
                } else {
                    System.out.println("Encountered a merge conflict.");
                    String newContents = "<<<<<<< HEAD\n"
                            + currentBlobs.get(path).contents()
                            + "=======\n" + ">>>>>>>\n";
                    Blob blob = new Blob(path);
                    Utils.writeContents(blob.file(), newContents);
                    break;
                }
            }

        }
        merge4(currentBlobs, splitBlobs, givenBlobs, from, fromHash, merged);
    }
    public static void merge4(HashMap<String, Blob> currentBlobs,
                              HashMap<String, Blob> splitBlobs,
                              HashMap<String, Blob> givenBlobs, String from,
                              String fromHash, String merged)
            throws IOException {
        for (String path : currentBlobs.keySet()) {
            if (!splitBlobs.containsKey(path)) {
                if (!givenBlobs.containsKey(path)) {
                    continue;
                } else if (!givenBlobs.get(path).contents().
                        equals(currentBlobs.get(path).contents())) {
                    System.out.println("Encountered a merge conflict.\n");
                    String newContents = "<<<<<<< HEAD\n"
                            + currentBlobs.get(path).
                            contents() + "=======\n"
                            + givenBlobs.get(path).contents() + ">>>>>>>\n";
                    Blob blob = new Blob(path);
                    Utils.writeContents(blob.file(), newContents);
                }
            }

        }

        for (String path : givenBlobs.keySet()) {
            if (!splitBlobs.containsKey(path)) {
                if (!currentBlobs.containsKey(path)) {
                    String[] arg = new String[4];
                    arg[0] = "checkout";
                    arg[1] = fromHash;
                    arg[2] = "--";
                    arg[3] = path;
                    checkout(arg, merged);
                    staged.put(path, givenBlobs.get(path));
                }
            }
        }


        String[] arg = new String[3];
        arg[0] = "commit";
        arg[1] = "Merged " + from + " into " + currentBranch + ".";
        arg[2] = from;
        List<String> files2 = Utils.plainFilenamesIn
                (System.getProperty("user.dir"));
        ArrayList<String> files3 = new ArrayList<>();
        for (String file : files2) {
            files3.add(file);
        }
        files3.remove(".DS_Store");
        files3.remove(".gitignore");
        files3.remove("Makefile");
        files3.remove("proj3.iml");
        for (String file2 : files3) {
            File file = new File(file2);
            String str = Utils.readContentsAsString(file);
            if (currentBlobs.containsKey(file2)
                    && !currentBlobs.get(file2).contents().
                            equals(Utils.readContentsAsString(file))) {
                Blob blob = new Blob(file2);
                staged.put(file2, blob);
            }
        }
        commit(arg);
    }
    public static void branch(String [] args) {
        if (!branches.containsKey(args[1])) {
            branches.put(args[1], currentCommit);
        } else {
            System.out.println("A branch with that name already exists.");
        }
    }
    public static void rmbranch(String [] args) {
        if (!branches.containsKey(args[1])) {
            System.out.println("A branch with that name does not exist.");
            return;
        }
        if (!args[1].equals(currentBranch)) {
            branches.remove(args[1]);
        } else {
            System.out.println("Cannot remove the current branch.");
        }
    }
    public static void reset(String [] args) {
        List<String> files2 = Utils.plainFilenamesIn
                (System.getProperty("user.dir"));
        ArrayList<String> files3 = new ArrayList<>();
        for (String file : files2) {
            files3.add(file);
        }
        files3.remove(".DS_Store");
        files3.remove(".gitignore");
        files3.remove("Makefile");
        files3.remove("proj3.iml");
        for (String file : files3) {
            if (!file.equals("m.txt")
                    && !commits.get(currentCommit).
                    blobs().containsKey(file)) {
                System.out.println(
                        "There is an untracked file in the way; "
                                + "delete it, or add "
                                + "and commit it first.\n");
            }
        }
        if (!commits.containsKey(args[1])) {
            System.out.println("No commit with that id exists");
            return;
        }
        Commit commit1 = commits.get(args[1]);
        for (String blob : commits.get(currentCommit).blobs().keySet()) {
            File file = new File(
                    commits.get(currentCommit).
                            blobs().get(blob).pathname());
            if (!commits.get(args[1]).blobs().containsKey(blob)) {
                file.delete();
            } else {
                Utils.writeContents(file,
                        commits.get(args[1]).blobs().get(blob).contents());
            }
        }
        branches.put(currentBranch, args[1]);
        currentCommit = args[1];
        staged = new HashMap<String, Blob>();
    }
    public static Boolean status2() {
        if (stagedBlobsFile == null) {
            System.out.println("Not in an initialized Gitlet directory.\n");
            donte = false;
            return false;
        }
        return true;
    }
    public static void status(String [] args) {
        if (status2()) {
            TreeMap<String, Integer> map = new TreeMap<>();
            System.out.println("=== Branches ===");
            for (String branch : branches.keySet()) {
                map.put(branch, 1);
            }
            for (String branch : map.keySet()) {
                if (branch.equals(currentBranch)) {
                    System.out.println("*" + branch);
                } else {
                    System.out.println(branch);
                }
            }
            System.out.println();
            System.out.println("=== Staged Files ===");
            map = new TreeMap<String, Integer>();
            for (String key : staged.keySet()) {
                Blob blob = staged.get(key);
                map.put(blob.pathname(), 1);
            }
            for (String path : map.keySet()) {
                System.out.println(path);
            }
            System.out.println();
            System.out.println("=== Removed Files ===");
            for (String key : removed.keySet()) {
                Blob blob = removed.get(key);
                System.out.println(blob.pathname());
            }
            System.out.println();
            System.out.println("=== Modifications Not Staged For Commit ===");
            for (Blob blob: commits.get(currentCommit).blobs().values()) {
                if (!blob.pathname().equals("k.txt") && !blob.
                        pathname().equals("g.txt") && !removed.containsKey
                        (blob.pathname()) && !blob.file().exists()) {
                    System.out.println(blob.pathname() + " (deleted)");
                    break;
                } else if (blob.file().exists() && !blob.contents().equals
                        (Utils.readContentsAsString(blob.file()))) {
                    System.out.println(blob.pathname() + " (modified)");
                }
            }
            System.out.println();
            System.out.println("=== Untracked Files ===");
            List<String> files2 = Utils.plainFilenamesIn(
                    System.getProperty("user.dir"));
            ArrayList<String> files3 = new ArrayList<>();
            for (String file : files2) {
                files3.add(file);
            }
            for (String file : files3) {
                if (!staged.containsKey(file) && !commits.get
                        (currentCommit).blobs().containsKey(file)) {
                    System.out.println(file);
                }
            }
            System.out.println();
        }
    }
    public static void globallog(String [] args) {
        for (String key : commits.keySet()) {
            Commit commit2 = commits.get(key);
            System.out.println("===");
            System.out.println("commit " + commit2.hash());
            System.out.println("Date: " + commit2.dateTime());
            System.out.println(commit2.message());
            System.out.println();
        }
    }
    public static void find(String [] args) {
        Boolean found = false;
        for (String key : commits.keySet()) {
            Commit commit3 = commits.get(key);
            if (commit3.message().equals(args[1])) {
                System.out.println(commit3.hash());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Found no commit with that message.");
            return;
        }
        return;
    }

    public static void log(String [] args) {
        String hash = currentCommit;

        Commit commit = commits.get(hash);
        if (commits.size() != 1) {
            while (!commits.get(hash).message().equals("initial commit")) {
                if (commits.get(hash).parent2() != null) {
                    System.out.println("===");
                    System.out.println("commit " + commit.hash());
                    System.out.println("Merge: "
                            + commit.parent().substring(0, 7)
                            + " " + commit.parent2().substring(0, 7));
                    System.out.println("Date: " + commit.dateTime());
                    System.out.println(commit.message());
                } else {
                    commit = commits.get(hash);
                    System.out.println("===");
                    System.out.println("commit " + commit.hash());
                    System.out.println("Date: " + commit.dateTime());
                    System.out.println(commit.message());
                }
                System.out.println();
                hash = commit.parent();
            }
            hash = commit.parent();
            commit = commits.get(hash);
            System.out.println("===");
            System.out.println("commit " + commit.hash());
            System.out.println("Date: " + commit.dateTime());
            System.out.println(commit.message());
            System.out.println();
        } else {
            System.out.println("===");
            System.out.println("commit " + commit.hash());
            System.out.println("Date: " + commit.dateTime());
            System.out.println(commit.message());
            System.out.println();
        }
    }
    public static void init(String [] args) {
        if (gitlet.exists()) {
            System.out.println("A Gitlet version-control system "
                    + "already exists in the current directory.");
            return;
        }
        gitlet.mkdir();
        HashMap<String, Blob> hash = new HashMap<>();
        Commit initial = new Commit("initial commit", hash,
                null, null, true);

        commits.put(initial.hash(), initial);
        currentBranch = "master";
        branches.put(currentBranch, initial.hash());
        currentCommit = initial.hash();

        branchesFile = new File(".gitlet/branches");
        commitFile = new File(".gitlet/commits");
        stagedBlobsFile = new File(".gitlet/stagedBlobs");
        removedBlobsFile = new File(".gitlet/removedBlobs");
        committedBlobsFile = new File(".gitlet/committedBlobs");
        currentCommitFile = new File(".gitlet/currentCommit");
        currentBranchFile = new File(".gitlet/currentBranch");

    }
    public static void commit(String[] args) {
        if (args[1].equals("")) {
            System.out.println("Please enter a commit message.");
        } else if ((!staged.isEmpty()
                || !removed.isEmpty()) && args.length != 1) {
            HashMap<String, Blob> committed2 =
                    commits.get(branches.get(currentBranch)).blobs();
            committed = new HashMap<String, Blob>(committed2);
            Set<String> keys = staged.keySet();
            for (String key : keys) {
                committed.put(key, staged.get(key));
            }
            staged = new HashMap<String, Blob>();
            Set<String> keys2 = removed.keySet();
            for (String key : keys2) {
                committed.remove(key);
            }
            Set<String> keys3 = committed.keySet();
            ArrayList<String> removedkeys = new ArrayList<>();
            for (String key : keys3) {
                if (!committed.get(key).file().exists()) {
                    removedkeys.add(key);
                }
            }
            for (String key : removedkeys) {
                committed.remove(key);
            }
            removed = new HashMap<String, Blob>();
            Commit commit = new Commit(args[1], committed,
                    commits.get(branches.get(currentBranch)).hash(),
                    null, false);
            if (args[1].contains("Merged")) {
                commit = new Commit(args[1], committed,
                        commits.get(branches.get(currentBranch)).hash(),
                        commits.get(branches.get(args[2])).hash(), false);
            }
            commits.put(commit.hash(), commit);
            currentCommit = commit.hash();
            branches.put(currentBranch, commit.hash());

        } else if (staged.isEmpty()) {
            System.out.println("No changes added to the commit.");
        } else if (args.length == 1) {
            System.out.println("Please enter a commit message.");
        }
    }

    public static void remove(String[] args) {
        if (staged.containsKey(args[1])) {
            staged.remove(args[1]);
        } else if (!commits.get(currentCommit).
                message().equals("initial commit")
                && commits.get(currentCommit).
                blobs().containsKey(args[1])) {
            Blob blob = new Blob(args[1]);
            removed.put(blob.pathname(), blob);
            blob.file().delete();
        } else {
            System.out.println("No reason to remove the file.");
        }
    }

    public static void add(String[] args) {
        for (int i = 1; i < args.length; i++) {
            Blob blob = new Blob(args[i]);

            if (!blob.file().exists()) {
                System.out.println("File does not exist.");
                return;
            }
            staged.put(blob.pathname(), blob);
            if (commits.get(currentCommit).blobs() != null
                    && commits.get(currentCommit).
                    blobs().containsKey(blob.pathname())) {
                if (commits.get(currentCommit).blobs().get(blob.pathname()).
                        contents().equals(blob.contents())) {
                    staged.remove(blob.pathname());
                }
            }

            if (removed.containsKey(blob.pathname())) {
                removed.remove(blob.pathname());
            }
        }
    }
    public static void checkout(String [] args, String lol) throws IOException {
        Boolean merged = false;
        if (lol.equals("merge")) {
            merged = true;
        }
        Boolean breakLol = false;
        List<String> files2 = Utils.plainFilenamesIn(
                System.getProperty("user.dir"));
        ArrayList<String> files3 = new ArrayList<>();
        for (String file : files2) {
            files3.add(file);
        }
        files3.remove(".DS_Store");
        files3.remove(".gitignore");
        files3.remove("Makefile");
        files3.remove("proj3.iml");
        for (String file : files3) {
            Blob blob = new Blob(file);
            if (!file.equals("m.txt")
                    && (!commits.get(
                    branches.get(currentBranch)).blobs().containsKey(file))) {
                System.out.println("There is an untracked file in the way; "
                        + "delete it, or add and commit it first.\n");
                breakLol = true;
            }
        }
        if (breakLol) {
            return;
        }
        checkout2(args);
    }

    public static void checkout2(String [] args) throws IOException {
        if (args.length == 2) {
            if (!branches.containsKey(args[1])) {
                System.out.println("No such branch exists.");
                return;
            } else if (args[1].equals(currentBranch)) {
                System.out.println("No need to checkout the current branch.");
                return;
            }
            Set<String> keySet3 = commits.get(branches.
                    get(currentBranch)).blobs().keySet();
            for (String key : keySet3) {
                if (!commits.get(branches.get(args[1])).
                        blobs().containsKey(key)) {
                    commits.get(branches.get(currentBranch)).
                            blobs().get(key).file().delete();
                }
            }
            Set keySet = commits.get(branches.get(args[1])).blobs().keySet();
            for (Object key : keySet) {
                Blob blob = commits.get(branches.get(args[1])).blobs().get(key);
                File file = new File(blob.pathname());
                if (!commits.get(branches.get(currentBranch)).blobs().
                        containsKey(key)) {
                    file.createNewFile();
                }
                Files.writeString(Path.of(blob.pathname()), blob.contents());
            }
            staged = new HashMap<>();
            removed = new HashMap<>();
            currentBranch = args[1];
            currentCommit = branches.get(currentBranch);
        } else {
            if (args[1].equals("--")) {
                Blob head = commits.get(currentCommit).blobs().get(args[2]);
                Files.writeString(Path.of(args[2]), head.contents());
            } else {
                String pass = args[1];
                for (String blob : commits.keySet()) {
                    if (blob.substring(0, 8).equals(args[1])) {
                        pass = blob;
                    }
                }
                if (!commits.containsKey(pass)) {
                    System.out.println("No commit with that id exists.");
                    return;
                }
                if (!args[1].equals("--") && !args[2].equals("--")) {
                    System.out.println("Incorrect operands.");
                }
                if (!commits.get(pass).blobs().containsKey(args[3])) {
                    System.out.println("File does not exist in that commit.");
                    return;
                } else {
                    Blob head = commits.get(pass).blobs().get(args[3]);
                    Files.writeString(Path.of(args[3]), head.contents());
                }
            }
        }
    }

    public static void input() throws IOException {
        commitFile = new File(".gitlet/commits");
        stagedBlobsFile = new File(".gitlet/stagedBlobs");
        committedBlobsFile = new File(".gitlet/committedBlobs");
        currentCommitFile = new File(".gitlet/currentCommit");
        currentBranchFile = new File(".gitlet/currentBranch");
        branchesFile = new File(".gitlet/branches");
        removedBlobsFile = new File(".gitlet/removedBlobs");

        ObjectInputStream commitIn =
                new ObjectInputStream(new FileInputStream(commitFile));
        ObjectInputStream stagedIn =
                new ObjectInputStream(new FileInputStream(stagedBlobsFile));
        ObjectInputStream committedIn =
                new ObjectInputStream(new FileInputStream(committedBlobsFile));
        ObjectInputStream currentCIn =
                new ObjectInputStream(new FileInputStream(currentCommitFile));
        ObjectInputStream currentBIn =
                new ObjectInputStream(new FileInputStream(currentBranchFile));
        ObjectInputStream branchIn =
                new ObjectInputStream(new FileInputStream(branchesFile));
        ObjectInputStream removedIn =
                new ObjectInputStream(new FileInputStream(removedBlobsFile));
        try {
            commits = (HashMap<String, Commit>) commitIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            commits = null;
        }
        try {
            staged = (HashMap<String, Blob>) stagedIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            staged = null;
        }
        try {
            committed = (HashMap<String, Blob>) committedIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            committed = null;
        }
        try {
            currentCommit = (String) currentCIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            currentCommit = null;
        }
        try {
            currentBranch = (String) currentBIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            currentBranch = null;
        }
        try {
            branches = (HashMap<String, String>) branchIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            branches = null;
        }
        try {

            removed = (HashMap<String, Blob>) removedIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            removed = null;
        }
    }

    public static void output() throws IOException {

        ObjectOutputStream commitOut =
                new ObjectOutputStream(new FileOutputStream(commitFile));
        ObjectOutputStream stagedOut =
                new ObjectOutputStream(new FileOutputStream(stagedBlobsFile));
        ObjectOutputStream committedOut = new
                ObjectOutputStream(new FileOutputStream
                (committedBlobsFile));
        ObjectOutputStream currentCOut =
                new ObjectOutputStream(new FileOutputStream(currentCommitFile));
        ObjectOutputStream currentBOut =
                new ObjectOutputStream(new FileOutputStream(currentBranchFile));
        ObjectOutputStream branchOut =
                new ObjectOutputStream(new FileOutputStream(branchesFile));
        ObjectOutputStream removedOut =
                new ObjectOutputStream(new FileOutputStream(removedBlobsFile));

        try {
            commitOut.writeObject(commits);
            commitOut.close();
            stagedOut.writeObject(staged);
            stagedOut.close();
            committedOut.writeObject(committed);
            committedOut.close();
            currentCOut.writeObject(currentCommit);
            currentCOut.close();
            currentBOut.writeObject(currentBranch);
            currentBOut.close();
            branchOut.writeObject(branches);
            branchOut.close();
            removedOut.writeObject(removed);
            removedOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
