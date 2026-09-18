import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println(
                    "Usage: java Main data.csv [--filter col=val] [--sort col] [--group col --aggregate op:col]");
            return;
        }

        String file = args[0];
        String filterCol = null, filterVal = null;
        String sortCol = null;
        String groupCol = null;
        String aggOp = null, aggCol = null;

        // parse args
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("--filter") && i + 1 < args.length) {
                String[] parts = args[++i].split("=", 2);
                if (parts.length == 2) {
                    filterCol = parts[0];
                    filterVal = parts[1];
                }
            } else if (args[i].equals("--sort") && i + 1 < args.length) {
                sortCol = args[++i];
            } else if (args[i].equals("--group") && i + 1 < args.length) {
                groupCol = args[++i];
            } else if (args[i].equals("--aggregate") && i + 1 < args.length) {
                String[] parts = args[++i].split(":", 2);
                if (parts.length == 2) {
                    aggOp = parts[0];
                    aggCol = parts[1];
                }
            }
        }

        List<String[]> rows = new ArrayList<>();
        String[] headers = null;

        // load file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            if ((line = br.readLine()) != null) {
                headers = line.split(",");
            }
            while ((line = br.readLine()) != null) {
                rows.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        if (headers == null) {
            System.out.println("File is empty.");
            return;
        }

        List<String> headerList = Arrays.asList(headers);

        // filter data
        if (filterCol != null && filterVal != null) {
            int colIndex = headerList.indexOf(filterCol);
            if (colIndex != -1) {
                List<String[]> filtered = new ArrayList<>();
                for (String[] row : rows) {
                    if (row.length > colIndex && row[colIndex].trim().equals(filterVal)) {
                        filtered.add(row);
                    }
                }
                rows = filtered;
            } else {
                System.out.println("Filter column not found.");
            }
        }

        // handle grouping and math
        if (groupCol != null && aggOp != null && aggCol != null) {
            int gIndex = headerList.indexOf(groupCol);
            int aIndex = headerList.indexOf(aggCol);

            if (gIndex != -1 && aIndex != -1) {
                Map<String, List<Double>> groups = new LinkedHashMap<>();
                for (String[] row : rows) {
                    if (row.length > gIndex && row.length > aIndex) {
                        String key = row[gIndex].trim();
                        try {
                            double val = Double.parseDouble(row[aIndex].trim());
                            groups.putIfAbsent(key, new ArrayList<>());
                            groups.get(key).add(val);
                        } catch (NumberFormatException e) {
                            // ignore bad numbers in math
                        }
                    }
                }

                System.out.println(groupCol + "," + aggOp + "_" + aggCol);
                for (Map.Entry<String, List<Double>> entry : groups.entrySet()) {
                    List<Double> vals = entry.getValue();
                    if (vals.isEmpty())
                        continue;

                    double res = 0;
                    switch (aggOp.toLowerCase()) {
                        case "sum":
                            for (double v : vals)
                                res += v;
                            break;
                        case "avg":
                            double sum = 0;
                            for (double v : vals)
                                sum += v;
                            res = sum / vals.size();
                            break;
                        case "min":
                            res = Collections.min(vals);
                            break;
                        case "max":
                            res = Collections.max(vals);
                            break;
                        case "count":
                            res = vals.size();
                            break;
                        default:
                            System.out.println("Unknown op: " + aggOp);
                            return;
                    }
                    System.out.println(entry.getKey() + "," + res);
                }
                return;
            } else {
                System.out.println("Group or  aggregate column not found.");
                return;
            }
        }

        // sort data
        if (sortCol != null) {
            int colIndex = headerList.indexOf(sortCol);
            if (colIndex != -1) {
                rows.sort((r1, r2) -> {
                    String v1 = r1.length > colIndex ? r1[colIndex].trim() : "";
                    String v2 = r2.length > colIndex ? r2[colIndex].trim() : "";

                    try {
                        double d1 = Double.parseDouble(v1);
                        double d2 = Double.parseDouble(v2);
                        return Double.compare(d1, d2);
                    } catch (NumberFormatException e) {
                        return v1.compareToIgnoreCase(v2);
                    }
                });
            } else {
                System.out.println("Sort column not found.");
            }
        }

        // print results
        System.out.println(String.join(",", headers));
        for (String[] row : rows) {
            System.out.println(String.join(",", row));
        }
    }
}
