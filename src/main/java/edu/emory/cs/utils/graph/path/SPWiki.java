package edu.emory.cs.utils.graph.path;

import edu.emory.cs.utils.graph.Graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SPWiki
{
    List<String> titles;
    int[][] links;

    public SPWiki(InputStream inTitles, InputStream inLinks) throws Exception
    {
        titles = getTitles(inTitles);
        links  = getLinks(inLinks, titles.size());

        Graph g = new Graph(titles.size());
        Dijkstra d = new Dijkstra();
        int source = 0;
        int target = 0;

        // TODO:
        System.out.println(d.getShortestPath(g, source, target));
    }

    public List<String> getTitles(InputStream in) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        List<String> list = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null)
            list.add(line.trim());

        reader.close();
        return list;
    }

    public int[][] getLinks(InputStream in, int size) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Pattern p = Pattern.compile(" ");
        int[][] array = new int[size][];
        int[] links;
        String line;
        String[] t;
        int i, j;

        for (i=0; (line = reader.readLine()) != null; i++)
        {
            line = line.trim();

            if (line.isEmpty())
                array[i] = new int[0];
            else
            {
                t = p.split(line);
                links = new int[t.length];
                array[i] = links;

                for (j=0; j<links.length; j++)
                    links[j] = Integer.parseInt(t[j]);
            }
        }

        return array;
    }

    static public void main(String[] args) throws Exception
    {
        final String TITLE_FILE = "/Users/jdchoi/Emory/webpage/public_html/courses/cs323/dat/wiki-titles-small.txt";
        final String LINK_FILE  = "/Users/jdchoi/Emory/webpage/public_html/courses/cs323/dat/wiki-links-small.txt";
        new SPWiki(new FileInputStream(TITLE_FILE), new FileInputStream(LINK_FILE));
    }
}
