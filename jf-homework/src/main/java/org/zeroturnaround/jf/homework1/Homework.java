package org.zeroturnaround.jf.homework1;

import org.apache.commons.lang3.StringUtils;

public class Homework {

    public String reverse(String str) {
        return StringUtils.reverse(str);
    }

    /**
     * This method returns a common suffix for two strings.
     *
     * For example - given the arguments "firstString" and "secondString" the
     * method should return "String" as this is the common suffix for both.
     *
     * Another example - given the arguments "secondString" and "thirdString" the method
     * should return "dString" as this is the common suffix for those.
     *
     * If no common suffix exists for the argument strings, or if either of them is either
     * empty or null, the method should return an empty string.
     */
    public String getCommonSuffix(String first, String second) {

        // your implementation here!
        StringBuilder stringBuilder = new StringBuilder();
        // checking for empty strings
        if (first == null || second == null)
            return "One of the entered strings is null";
        if (first == "" || second == "")
            return "One of the entered strings is empty";

        int[][] ints = new int[first.length()][second.length()];
        int max = 0;
        int lastSubBegin = 0;
        int thisSubBegin;

        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    if ((i == 0) || (j == 0)){
                        ints[i][j] = 1;
                    } else {
                        ints[i][j] = 1 + ints[i-1][j-1];
                    }
                    if (ints[i][j] > max) {
                        max = ints[i][j];
                        thisSubBegin = i - ints[i][j] + 1;
                        if (lastSubBegin == thisSubBegin) {
                            stringBuilder.append(first.charAt(i));
                        } else {
                            lastSubBegin = thisSubBegin;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(first.substring(lastSubBegin, i+1));
                        }
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
