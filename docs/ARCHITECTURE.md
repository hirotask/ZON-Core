# ZON-Kills アーキテクチャ
本ドキュメントは、ZON-Kills(以下：本プラグイン)におけるソフトウェア設計およびアーキテクチャに関するものです。  

## 採用しているアーキテクチャ
本プラグインは[DDD](https://ja.wikipedia.org/wiki/%E3%83%89%E3%83%A1%E3%82%A4%E3%83%B3%E9%A7%86%E5%8B%95%E8%A8%AD%E8%A8%88)ならびに「[Onion Architecture](https://qiita.com/cocoa-maemae/items/e3f2eabbe0877c2af8d0)」を参考にして開発しています。  
Onion Architectureは以下の円形の図、またはブロックで表されます。  
![](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F55214%2F0ba5f566-f5bc-cddf-92e6-3bbef5040e42.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&w=1400&fit=max&s=ca28995b47eb0135293450f15d9e7733)  
![](https://qiita-user-contents.imgix.net/https%3A%2F%2Fqiita-image-store.s3.ap-northeast-1.amazonaws.com%2F0%2F55214%2Fed946eb6-6546-3dc0-2a2e-ab1f53754520.png?ixlib=rb-4.0.0&auto=format&gif-q=60&q=75&w=1400&fit=max&s=9f3f3d8426dfc0313cfd42317057b3a7)