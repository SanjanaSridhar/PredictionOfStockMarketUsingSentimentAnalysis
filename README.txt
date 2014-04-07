CREATE DATABASE:

Follow the steps given in DATABASE CREATION.txt


TRAINING PHASE


Steps in training phase:


TaggerDemo Project:

 
- Run test.java ( creates positive.txt, negative.txt and counts the number of positive and negative articles)
- Run Tagger.java ( calls TaggerDemo.java )
- Run FrequencyCount.java ( with positive and then run the same program again changing it to negative) 
  ** Now the database has all the words
- SQL
	->Find the number of words that have been entered -- select count(word) from words;
	->Find the sum of pcount and ncount --select sum(pcount) from words;
				            --select sum(ncount) from words;
	->Update the PwordPos and PwordNeg  --update words set PwordsPos=(pcount+1)/(<sum(pcount)>+<count(words)>);)
					    --update words set PwordNeg=(ncount+1)/(<sum(ncount)>+<count(words)>);)
			
			***** <> indicates use the value obtained in the previous query.



MessageBoard Project:

- Run MessageBoard.java to get the messages
- Run categorizingMessages.java program ( This creates two files positive and negative and puts the respective messages in them)
- Run the POSTaggingMessages.java program ( To POS tag all the messages and store them in pmessages and nmessages)
- Run FrequencycountMessages.java (separately for positive and negative)
- SQL 
 	->Find the number of words that have been entered -- select count(word) from messageboard_words;
	->Find the sum of pcount and ncount --select sum(pcount) from messageboard_words;
				            --select sum(ncount) from messageboard_words;
	->Update the PwordPos and PwordNeg  --update messageboard_words set PwordsPos=(pcount+1)/(<sum(pcount)>+<count(words)>);
					    --update messageboard_words set PwordsNeg=(ncount+1)/(<sum(ncount)>+<count(words)>);


						


TweetRetrieval Project:

- Run the TweetSearch program to retrieve the tweets specially for each company
- Run the DatabaseCreation program ( This creates two files positive and negative and puts the respective tweets in them)
- Run the POSTaggingTweets program ( To POS tag all the tweets and store them in ptweets and ntweets
- Run the FrequencyCountTweets.java (separately for positive and negative to add them into the database)					
- SQL 
 	->Find the number of words that have been entered -- select count(word) from twitter_words;
	->Find the sum of pcount and ncount -- select sum(pcount) from twitter_words;
				            -- select sum(ncount) from twitter_words;
	->Update the PwordPos and PwordNeg -- update twitter_words set PwordsPos=(pcount+1)/(<sum(pcount)>+<count(words)>);)
					   -- update twitter_words set PwordsNeg=(ncount+1)/(<sum(ncount)>+<count(words)>);)

Count Project:

- Run Count.java to populate count table in the database





TESTING PHASE

Steps in testing phase:

To Retrieve the stock quotes:
Download the data downloader from the link given below:
http://marketearnings.blogspot.in/2012/05/nse-free-data-downloader.html


TestingPhase project:

- Run PredictionOfStockMarket form.
- Press the Result button
	*This in turn calls,
		--TestingProgram.java
		--LiveMint.java
		--EconomicTimes.java
		--ExtractRSS.java
		--GetTweets.java
		--GetMessages.java
		--NaiveBayes.java
-Press the Feedback button
	*This in turn calls,
		--Feedback.java

						

Chart project:

- Run BarChartCount.java
- Run BarChartWordCount.java


