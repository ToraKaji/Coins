<h1>Coins!</h1>
<hr />
<ul>
<li>
<p>Coins is a fun penny popping game in which users try to match "coin values" in a row, column, or diagonal. If said user does match them they will recieve that many coins into there balance. Each play costs 18 of their available coins if they lose, if they win however, the amount that they align will be added to their balance.</p>
</li>
</ul>
<hr />
<h1>Current state</h1>
<ul>
<li>Coins is currently under a development period with not too much progress in the near future. Feel free to use any part of this code as your own. Hopefully I have helped someone develop an app of equal greatness ;)</li>
</ul>
<hr />
<h1>Third party libraries used</h1>
<p>implementation 'com.google.android.gms:play-services-auth:16.0.1'<br/> implementation 'de.hdodenhof:circleimageview:2.2.0'<br/> implementation 'com.squareup.picasso:picasso:2.71828' <br/>implementation 'com.facebook.stetho:stetho:1.5.0' implementation 'android.arch.persistence.room:runtime:1.1.1'<br/> annotationProcessor 'android.arch.persistence.room:compiler:1.1.1'</p>
<hr />
<h1>External services</h1>
<p>This app uses GoogleSignIn as an external service</p>
<hr />
<h1>User stories</h1>
<ul>
<ul>
<li>As a heavy Fortnite gamer I need something that can match the complexity of Fortnite.</li>
<li>As a bored teenager I need something to curb my curiosity and draw out the dullness in life</li>
<li>As a developer, I would like to see things I could improve upon within an app</li>
</ul>
</ul>
<hr />
<h1>DDL</h1>
<p>CREATE TABLE IF NOT EXISTS `Coin` (`coin_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,<br /> `coin_number` INTEGER NOT NULL,<br /> `play_id` INTEGER NOT NULL,<br /> `value` INTEGER NOT NULL,<br /> <br />FOREIGN KEY(`play_id`) REFERENCES `Play`(`play_id`) ON UPDATE NO ACTION ON DELETE CASCADE ); <br /><br /> CREATE INDEX `index_Coin_play_id` ON `Play` (`play_id`); <br /> <br />CREATE TABLE IF NOT EXISTS `Play`<br /> (`play_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,<br /> `ammount_won` INTEGER NOT NULL,<br /> `outcome` INTEGER NOT NULL);<br /> <br />CREATE UNIQUE INDEX `index_Play_play_id` ON `Play` (`play_id`);<br /><br /> CREATE TABLE IF NOT EXISTS `User`<br /> (`user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,<br /> `new_user` INTEGER NOT NULL,<br /> `play_id` INTEGER NOT NULL,<br /> `user_name` TEXT,<br /> `time_played` INTEGER,<br /> `coins` INTEGER NOT NULL);</p>
<hr />
<h1>LICENCES</h1>
<p><a href="https://github.com/hdodenhof/CircleImageView/blob/master/LICENSE.txt">CircleImageView</a></p>
<hr />
<h1>Instructions for play</h1>
<p>Upon inital installation you will be brought to the welcome screen after you conitinue you will not be brought to that screen again. You will then be brought to the play history fragment. This will be blank as you have not yet played. If you click the drawer expansion button you will see "Penny Popper" this is the main game at the moment. Once you click it you will be brought to a screen with 9 coins. Click them at random to try and align 3 in a row, column, or diagonal. If you do you have aligned them, you will be rewarded with that value that you have aligned. The drawer expansion button will show you the current amount of coins you have available.</p>
<hr />
<h1>Instructions for build</h1>
<p>Clone the repository into a local Android Studio or Intellij project.<br /> Build the app onto a device running a minimum of API 21</p>
<hr/>
<h1>JavaDocs</h1>
<a href="https://github.com/ToraKaji/Coins/tree/master/docs/api">Link to JavaDocs</a>
