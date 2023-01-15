/* You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive)... You are also given a 0-indexed integer array persons of size n, where persons[i] is the time that the ith person will arrive to see the flowers... Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives...
 * Eg 1: flowers = [[1,6],[3,7],[9,12],[4,13]]     persons = [2,3,7,11]                    Output = [1,2,2,2]
 * Eg 2: flowers = [[1,10],[3,3]]                  persons = [3,3,2]                       Output = [2,2,1]
 * Eg 3: flowers = [[19,37],[19,38],[19,35]]       persons = [6,7,21,1,13,37,5,37,43,43]    Output = [0,0,3,0,0,2,0,2,0,0]
  */
import java.util.*;
public class Flower
{
    public int[] FullBloomingFlowers(int flowers[][], int persons[])
    {
        Arrays.sort(flowers, (a,b) -> a[0] - b[0]);    // Sorted the array in ascending order by their blooming time...
        for(int i = 0; i < flowers.length; i++)
            System.out.print("["+flowers[i][0]+", "+flowers[i][1]+"] ,");
        System.out.println();
        int arr[] = new int[persons.length];    // The number of people will determine the answer array...
        int time = 1, j = 0;
        PriorityQueue<Integer> bloom = new PriorityQueue<Integer>();   // Priority Queue regarding the earliest flower blooming...
        PriorityQueue<Integer> wilt = new PriorityQueue<Integer>();    // Priority Queue regarding the earliest flower wilting...
        List<Integer> flowersBloomed = new ArrayList<Integer>();    // List to store the number of flowers bloomed at ith time...
        while(time < 10000)
        {
            if(time == flowers[j][0])    // If a flower bloomed at that instant...
            {
                bloom.add(flowers[j][0]);    // Adding the flower to the Queue...
                wilt.add(flowers[j][1]+1);   // Every blooming flower will wilt also...
                if(j < flowers.length - 2 && flowers[j][0] == flowers[j+1][0])
                {   // If more than one flower blooms at the same instant...
                    while(flowers[j][0] == flowers[j+1][0])
                    {
                        bloom.add(flowers[j+1][0]);
                        wilt.add(flowers[j+1][1]+1);
                        if(j < flowers.length - 2)
                            j++;
                        else
                            break;
                    }
                }
                if(j < flowers.length - 1)
                    j++;    // Incrementing the flower blooming index...
            }
            if(!wilt.isEmpty())
            {
                if(time == wilt.peek())    // If at that moment the flower wilts...
                {
                    wilt.remove();
                    bloom.remove();
                }
            }
            flowersBloomed.add(time-1, bloom.size());    // Initializing the blooming flowers at every instant...
            time++;
        }
        for(int i = 0; i < persons.length; i++)
            arr[i] = flowersBloomed.get(persons[i]-1);    // Getting the number of flowers bloomed when the ith person arrives at its given time...
        System.out.println("The Flowers seen by the People are : ");
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i]+", ");
        return arr;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the number of Flowers that will bloom : ");
        x = sc.nextInt();
        int flower[][] = new int[x][2];
        for(int i = 0; i < flower.length; i++)
        {
            System.out.print("Enter the Blooming Time of "+(i+1)+" th Flower : ");
            flower[i][0] = sc.nextInt();
            System.out.print("Enter the Wilting Time of "+(i+1)+" th Flower : ");
            flower[i][1] = sc.nextInt();
        }
        System.out.print("Enter the number of People visiting : ");
        x = sc.nextInt();
        int people[] = new int[x];
        for(int i = 0; i < people.length; i++)
        {
            System.out.print("Enter the Visiting time of the "+(i+1)+" th Person : ");
            people[i] = sc.nextInt();
        }
        Flower flowering = new Flower();    // Object creation...
        people = flowering.FullBloomingFlowers(flower, people);    // Function call...
        sc.close();
    }
}

// Time Complexity  - O(n log n) time...
// Space Complexity - O(n) space...             The space of the List is the maximum...

/* DEDUCTIONS :- 
 * 1. Since the arrays are unsorted we need to sort the arrays...
 * 2. The wilting and blooming of flowers can be maintained using the Priority Queues for each of them...
 * 3. The list can store the number of flowers bloomed at every instant, later this list formed can be used to get the number of flowers blooming when each person arrives at the specified time, this ensures that even if the person array is unsorted we can determine the blooming flowers for every person...
*/