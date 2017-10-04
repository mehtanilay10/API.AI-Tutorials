using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ApiAiSDK;
using ApiAiSDK.Model;


namespace DotNetCLient
{
    class Program
    {
        static void Main(string[] args)
        {
            ApiAi agent = new ApiAi(new AIConfiguration("0a2b60ccc88b48c38779ef6806d76539", SupportedLanguage.English));
            Console.WriteLine("Welcome to API.AI C# client");

            while(true)
            {
                Console.Write("You: ");
                string query = Console.ReadLine();
                var response = agent.TextRequest(query);
                if (response.Status.Code == 200)
                    Console.WriteLine("Bot: " + response.Result.Fulfillment.Speech);
                else
                    Console.WriteLine(response.Status.ErrorDetails);
            }
        }
    }
}
