package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MyGdxGame extends ApplicationAdapter {

	enum Screen{
		TITLE, MAIN_GAME, GAME_OVER;
	}
	
	Screen currentScreen = Screen.MAIN_GAME;

	Utils utils = new Utils();
	GameBasicStrings gameBasicStrings = new GameBasicStrings();
	GameOver gameOver = new GameOver();
	

	private SpriteBatch batch;
	
	private TextureAtlas commentBox; 
	
	private SimpleAnimation animationGlobulo;
	private SimpleAnimation animationCorona;
	private SimpleAnimation animationGlobuloAttack;
	private SimpleAnimation animationCoronaAttack;
	private SimpleAnimation animationGlobuloDef;
	private SimpleAnimation animationCoronaDef;
	private Animation animationCommentBox;
	

	private float elapsedTime = 0f;
	private OrthographicCamera camera;
	private Texture texture;
	private Sprite sprite;
	private Texture bandeiraTextura;
	private Texture flagTexture;

	private ShapeRenderer globuloLifeBar;
	private ShapeRenderer coronaLifeBar;


	private int globuloLife = 300;
	private int coronaLife = 300;

	private Color globuloColor = Color.GREEN;
	private Color coronaColor = Color.GREEN;


	

	private float globuloPosX = -600;
	private float globuloPosY = -100;
	private float coronaPosX = 150;
	private float coronaPosY = -100;

	private float attackGlobuloPos = globuloPosX+50;
	private float attackCoronaPos = coronaPosX;

	private boolean basicAttackGlobulo = false;
	private boolean basicAttackCorona = false;
	private boolean userInteraction = true;
	private boolean nextCommentPermission = true;
	private boolean coronaLoseLife = false;
	private boolean roundBegin = true;
	private boolean globuloTurn = false;
	private boolean coronaTurn = false;
	private boolean coronaWillDefence = false;
	private boolean globuloEnd = false;
	private boolean coronaEnd = false;
	private boolean showBar = false;
	private boolean globuloDefence = false;
	private boolean coronaDefence = false;
	private boolean coronaControl;
	private boolean firstRound = true;
	private boolean portuguese = true;

	private float beginGLobuloDefence = 0;
	private float beginCoronaDefence = 0;
	private float beginCoronaTime = 0;
	private float beginButtonTime = 0;

	private int globuloAttackHitRate;
	private int coronaAttackHitRate;
	private int globuloDefenceHitRate;
	private int coronaDefenceHitRate;
	private int coronaDamageInt;
	private int globuloDamageInt;
	private int commentID = 0;


	private BitmapFont font;
	
	private String string = "Iniciar Jogo  -  [ENTER]";

	private SimpleMusic musicTheme;
	private SimpleMusic musicLowLife;
	private SimpleMusic musicMenu;
	private Sound soundAttack;
	private Sound soundDefence;
	

	private SimpleButton buttonBrazil;
	private SimpleButton buttonEngland;

	


	


	
 
	
	@Override
	public void create () {
	   musicTheme = new SimpleMusic(0.2f, true, "sounds/battleTheme.mp3");
	   musicMenu = new SimpleMusic(0.2f, true, "sounds/begin.mp3");
	   musicLowLife = new SimpleMusic(0.2f, true, "sounds/lowLife.mp3");
	   musicMenu.play();
	   soundAttack = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.ogg"));
	   soundDefence = Gdx.audio.newSound(Gdx.files.internal("sounds/defence.ogg"));

	   globuloLifeBar = new ShapeRenderer();
	   coronaLifeBar = new ShapeRenderer();

	   font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));

	   texture = new Texture(Gdx.files.internal("bodyMap.jpg"));
	   bandeiraTextura = new Texture(Gdx.files.internal("brasil.png"));
	   flagTexture = new Texture(Gdx.files.internal("inglaterra.png"));
	   buttonBrazil = new SimpleButton(bandeiraTextura, -620, -300, 100,50);
	   buttonEngland = new SimpleButton(flagTexture, -620, -300, 100,50);
	   sprite = new Sprite(texture, 0,0, 711, 400);
	   sprite.setPosition(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*2.3f, Gdx.graphics.getWidth()-Gdx.graphics.getWidth()*1.45f);
	   sprite.setSize(1280, 720);
	
	   batch = new SpriteBatch();
	   camera = new OrthographicCamera(1280, 720);
	   camera.update();

	   commentBox = new TextureAtlas(Gdx.files.internal("atlas/comment.atlas"));
	   
	   animationGlobulo = new SimpleAnimation("atlas/globulo.atlas");
	   animationCorona = new SimpleAnimation("atlas/corona.atlas");
	   animationGlobuloAttack = new SimpleAnimation("atlas/globuloAttack.atlas");
	   animationCoronaAttack = new SimpleAnimation("atlas/coronaAttack.atlas");
	   animationCoronaDef = new SimpleAnimation("atlas/defCorona.atlas");
	   animationGlobuloDef = new SimpleAnimation("atlas/globuloDef.atlas");
	   animationCommentBox = new Animation(1f/6f, commentBox.getRegions());
	   
	   Gdx.input.setInputProcessor(new InputAdapter() {
	  	 @Override
	  	 public boolean keyDown (final int keyCode){
		    if(userInteraction && (keyCode == Input.Keys.A || keyCode == Input.Keys.D) && commentID == 1){
				if(keyCode == Input.Keys.A){
					basicAttackGlobulo = true;
					userInteraction = false;
				}
				else if(keyCode == Input.Keys.D){
					globuloDefence = true;
					beginGLobuloDefence = elapsedTime;
					userInteraction = false;
					soundDefence.play(0.2f);
				}
				
				globuloTurn = true;
			}

			if(keyCode == Input.Keys.ENTER && (nextCommentPermission)){
				if(firstRound){
					musicTheme.play();
					musicMenu.stop();
					firstRound = false;
					showBar = true;
				}
				commentID = gameBasicStrings.nextString(string);
				if(!portuguese)
					string = gameBasicStrings.getString(commentID);
				else
					string = gameBasicStrings.pegaString(commentID);
				nextCommentPermission = utils.permitComment(commentID);
			}
			if(currentScreen == Screen.GAME_OVER && keyCode == Input.Keys.ENTER){
				coronaLife = 300;
				globuloLife = 300;
				currentScreen = Screen.MAIN_GAME;
				firstRound = true;
				musicMenu.play();
				if(!portuguese)
					string = "Game Start   -   [ENTER]";
				else
					string = "Iniciar Jogo  -  [ENTER]";
				commentID = 0;
				nextCommentPermission = true;
			}

			return true;
	   }
	   });
	}
 
	@Override
	public void dispose() {
	   batch.dispose();
	}
 
	@Override
	public void render () {
		if(currentScreen == Screen.MAIN_GAME){
			elapsedTime += Gdx.graphics.getDeltaTime();

			if(roundBegin && commentID == 1){//begin round with random status

				globuloAttackHitRate = utils.random(101);
				globuloDefenceHitRate = utils.random(101);
				globuloDamageInt = utils.random(101);
				coronaAttackHitRate = utils.random(101);
				coronaDefenceHitRate = utils.random(101);
				coronaDamageInt = utils.random(101);

				

				if(coronaAttackHitRate < 30){
					coronaWillDefence = true;	
					coronaDamageInt = 0;
				}
				
				if(globuloAttackHitRate < 30)
					globuloDamageInt = 0;
				
				


				CoronaQuotes quote = new CoronaQuotes();
				if(!portuguese)
					string = quote.returnCoronaQuote(coronaDamageInt, globuloDamageInt, coronaAttackHitRate, globuloAttackHitRate);
				else
					string = quote.retornaFalaDoCorona(coronaDamageInt, globuloDamageInt, coronaAttackHitRate, globuloAttackHitRate);
				nextCommentPermission = true;
				roundBegin = false;
				
			}
			if(coronaTurn){
				nextCommentPermission = false;
				beginCoronaTime = elapsedTime;
				coronaTurn = false;
				coronaControl = true;
			}
			else if(elapsedTime > beginCoronaTime+2 && coronaControl){		
				coronaControl = false;
				if(coronaWillDefence){
					coronaDamageInt = 0;
					coronaDefence = true;
					soundDefence.play(0.2f);
					beginCoronaDefence = elapsedTime;
				}
				else{
					basicAttackCorona = true;
				}
			}

			
		
		if(elapsedTime > beginGLobuloDefence + 2 && globuloDefence){//end globulo Defence
				globuloDefence = false;
				commentID = gameBasicStrings.nextStringGlobuloDefense(globuloDefenceHitRate);
				if(!portuguese)
					string = gameBasicStrings.getString(commentID);
				else
					string = gameBasicStrings.pegaString(commentID);
				nextCommentPermission = true;
				globuloTurn = false;
				coronaTurn = true;
				userInteraction = true;
				globuloDamageInt = 0;
				globuloEnd = true;
		}
		if(elapsedTime > beginCoronaDefence + 2 && coronaDefence){//end corona defence
				coronaDefence = false;
				coronaTurn = false;
				coronaEnd = true;
				coronaWillDefence = false;
				commentID = gameBasicStrings.coronaDefense(coronaDefenceHitRate);
				if(!portuguese)
					string = gameBasicStrings.getString(commentID);
				else
					string = gameBasicStrings.pegaString(commentID);
				nextCommentPermission = true;
				coronaLife -= utils.decreaseLife(globuloDamageInt, coronaDefenceHitRate);
				coronaLoseLife = true;
		}
			


		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		batch.begin();
		
		sprite.draw(batch);
		if(portuguese){
			buttonBrazil.update(batch, Gdx.input.getX(), Gdx.input.getY());
			boolean click = buttonBrazil.clicked();
			if(click && elapsedTime > beginButtonTime + 1){
				portuguese = false;
				beginButtonTime = elapsedTime;
				string = gameBasicStrings.getString(commentID);
			}
		}
		else{
			buttonEngland.update(batch, Gdx.input.getX(), Gdx.input.getY());
			boolean click = buttonEngland.clicked();
			if(click && elapsedTime > beginButtonTime + 1){
				portuguese = true;
				beginButtonTime = elapsedTime;
				string = gameBasicStrings.pegaString(commentID);
			}
		}

		
		
		batch.setProjectionMatrix(camera.combined);
		

		globuloLifeBar.begin(ShapeType.Filled);
		coronaLifeBar.begin(ShapeType.Filled);
		globuloLifeBar.setColor(globuloColor);
		coronaLifeBar.setColor(coronaColor);
		globuloColor = utils.checkLifeBar(globuloLife);
		coronaColor = utils.checkLifeBar(coronaLife);

		if(globuloColor == Color.RED){
			musicTheme.stop();
			musicLowLife.play();
		}

		if(showBar){
			globuloLifeBar.rect(10,120,10,globuloLife);
			coronaLifeBar.rect(770,120,10,coronaLife);
		}

		
		

		
		animationGlobulo.update(batch, elapsedTime, globuloPosX, globuloPosY);
		batch.draw((TextureRegion)animationCommentBox.getKeyFrame(elapsedTime, true), -450, -525);
		if(globuloDefence){
				//batch.draw((TextureRegion)animationGlobuloDef.getKeyFrame(elapsedTime, true), globuloPosX, globuloPosY);
				animationGlobuloDef.update(batch, elapsedTime, globuloPosX, globuloPosY);
				if(!portuguese)
					string = "			      *Defending*";
				else
					string = "			      *Defendendo*";
				
			}
				
		
		animationCorona.update(batch, elapsedTime, coronaPosX, coronaPosY);
		

		if(coronaDefence){
				animationCoronaDef.update(batch, elapsedTime, coronaPosX, coronaPosY);
				nextCommentPermission = false;
				if(!portuguese)
					string = "  *Corona Defending*";
				else
					string = "  *Corona Denfendendo*";
				
		}
		if(basicAttackGlobulo && attackGlobuloPos < coronaPosX && globuloTurn){   
				animationGlobuloAttack.update(batch, elapsedTime, attackGlobuloPos, globuloPosY+50);
				attackGlobuloPos += 8;
				if(!portuguese)
					string = "      *Attacking*";
				else
					string = "       *Atacando*";
				
			}
			else if(globuloTurn && basicAttackGlobulo){
				commentID = gameBasicStrings.nextStringGlobuloAttack(globuloDamageInt, globuloAttackHitRate);
				if(!portuguese)
					string = gameBasicStrings.getString(commentID);
				else
					string = gameBasicStrings.pegaString(commentID);
				nextCommentPermission = true;
				basicAttackGlobulo = false;
				attackGlobuloPos = globuloPosX+50;
				globuloTurn = false;
				coronaTurn = true;
				userInteraction = true;
				globuloDefenceHitRate = 0;
				globuloEnd = true;
				if(globuloAttackHitRate>=30)
					soundAttack.play(0.2f);
				if(!coronaWillDefence){
					coronaDefenceHitRate = 0;
					coronaLife -= utils.decreaseLife(globuloDamageInt, coronaDefenceHitRate); 
					coronaLoseLife = true;
				}
			}
			
		
			
			
		if(basicAttackCorona && attackCoronaPos > globuloPosX+100){
				animationCoronaAttack.update(batch, elapsedTime, attackCoronaPos, coronaPosY+50);
				attackCoronaPos -= 8;
				nextCommentPermission = false;
				if(!portuguese)
					string = "   *Corona Attacking*";
				else
					string = "   *Corona Atacando*";
		}
		else if(basicAttackCorona){
			basicAttackCorona = false;
			attackCoronaPos = coronaPosX;
			commentID = gameBasicStrings.coronaStringAttack(coronaAttackHitRate, coronaDamageInt);
			if(!portuguese)
				string = gameBasicStrings.getString(commentID);
			else
				string = gameBasicStrings.pegaString(commentID);
			coronaEnd = true;
			nextCommentPermission = true;

			soundAttack.play(0.2f);
		}

		if(coronaEnd && globuloEnd){
			coronaEnd = false;
			globuloEnd = false;
			roundBegin = true;
			if(!coronaLoseLife)
				coronaLife -= utils.decreaseLife(globuloDamageInt, coronaDefenceHitRate);
			globuloLife -= utils.decreaseLife(coronaDamageInt, globuloDefenceHitRate);
			coronaLoseLife = false;
		}

		


		font.draw(batch, string, -350, -180);
		batch.end();
		globuloLifeBar.end();
		coronaLifeBar.end();

		if(globuloLife <= 0 || coronaLife <= 0){
			musicLowLife.stop();
			musicTheme.stop();
			currentScreen = Screen.GAME_OVER;
		}


		}

		if(currentScreen == Screen.GAME_OVER){
			showBar = false;
			coronaTurn = false;
			gameOver.update(coronaLife, globuloLife, portuguese, batch, font);
		}


	}


	
}
