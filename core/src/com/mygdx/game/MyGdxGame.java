package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
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

	private SpriteBatch batch;

	private TextureAtlas globulo;
	private TextureAtlas corona;
	private TextureAtlas globuloDef;
	private TextureAtlas coronaDef;
	private TextureAtlas globuloAttack;
	private TextureAtlas coronaAttack;
	private TextureAtlas commentBox; 
	
	
	private Animation animationGlobulo;
	private Animation animationCorona;
	private Animation animationGlobuloAttack;
	private Animation animationCoronaAttack;
	private Animation animationGlobuloDef;
	private Animation animationCoronaDef;
	private Animation animationCommentBox;
	

	private float elapsedTime = 0f;
	private OrthographicCamera camera;
	private Texture texture;
	private Sprite sprite;

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

	private float beginGLobuloDefence = 0;
	private float beginCoronaDefence = 0;
	private float beginCoronaTime = 0;

	private int globuloAttackHitRate;
	private int coronaAttackHitRate;
	private int globuloDefenceHitRate;
	private int coronaDefenceHitRate;
	private int coronaDamageInt;
	private int globuloDamageInt;
	private int commentID = 0;
	private int score = 0;

	private BitmapFont font;

	private String string = "Game Start   -   [ENTER]";

	private Music musicTheme;
	private Music musicLowLife;
	private Music musicMenu;
	private Sound soundAttack;
	private Sound soundDefence;
	


	


	
 
	
	@Override
	public void create () {

	   
	   
	   musicTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/battleTheme.mp3"));
	   musicTheme.setVolume(0.2f);
	   musicTheme.setLooping(true);

	   musicMenu = Gdx.audio.newMusic(Gdx.files.internal("sounds/begin.mp3"));
	   musicMenu.setVolume(0.2f);
	   musicMenu.setLooping(true);
	   musicMenu.play();
	   
	   musicLowLife = Gdx.audio.newMusic(Gdx.files.internal("sounds/lowLife.mp3"));
	   musicLowLife.setVolume(0.08f);
	   musicLowLife.setLooping(true);

	   soundAttack = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.ogg"));
	   soundDefence = Gdx.audio.newSound(Gdx.files.internal("sounds/defence.ogg"));

	   
	   
	   		
	   globuloLifeBar = new ShapeRenderer();
	   coronaLifeBar = new ShapeRenderer();

	   font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));

	   texture = new Texture(Gdx.files.internal("bodyMap.jpg"));
	   sprite = new Sprite(texture, 0,0, 711, 400);
	   sprite.setPosition(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*2.3f, Gdx.graphics.getWidth()-Gdx.graphics.getWidth()*1.45f);
	   sprite.setSize(1280, 720);
	   batch = new SpriteBatch();
	   camera = new OrthographicCamera(1280, 720);
	   camera.update();

	   globulo = new TextureAtlas(Gdx.files.internal("atlas/globulo.atlas"));
	   corona = new TextureAtlas(Gdx.files.internal("atlas/corona.atlas"));
	   globuloAttack = new TextureAtlas(Gdx.files.internal("atlas/globuloAttack.atlas"));
	   coronaAttack = new TextureAtlas(Gdx.files.internal("atlas/coronaAttack.atlas"));
	   globuloDef = new TextureAtlas(Gdx.files.internal("atlas/globuloDef.atlas"));
	   coronaDef = new TextureAtlas(Gdx.files.internal("atlas/defCorona.atlas"));
	   commentBox = new TextureAtlas(Gdx.files.internal("atlas/comment.atlas"));
	   


	   animationGlobulo = new Animation(1f/6f, globulo.getRegions());
	   animationCorona = new Animation(1f/6f, corona.getRegions());
	   animationGlobuloAttack = new Animation(1f/6f, globuloAttack.getRegions());
	   animationCoronaAttack = new Animation(1f/6f, coronaAttack.getRegions());
	   animationCoronaDef = new Animation(1f/6f, coronaDef.getRegions());
	   animationGlobuloDef = new Animation(1f/6f, globuloDef.getRegions());
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
				commentID = utils.nextString(string);
				string = utils.getString(commentID);
				nextCommentPermission = utils.permitComment(commentID);
			}
			if(currentScreen == Screen.GAME_OVER && keyCode == Input.Keys.ENTER){
				score = 0;
				coronaLife = 300;
				globuloLife = 300;
				currentScreen = Screen.MAIN_GAME;
				firstRound = true;
				musicMenu.play();
				string = "Game Start   -   [ENTER]";
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
	   globulo.dispose();
	   corona.dispose();
	   musicTheme.dispose();
	   musicLowLife.dispose();
	   soundAttack.dispose();
	}
 
	@Override
	public void render () {

		if(currentScreen == Screen.MAIN_GAME){
			elapsedTime += Gdx.graphics.getDeltaTime();
			//System.out.println("commentId:"+commentID+"\n"+"permission:"+nextCommentPermission);

			if(roundBegin == true && commentID == 1){//begin round with random status

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
				
				
				System.out.println("GlobuloAttack:"+globuloDamageInt);

				CoronaQuotes quote = new CoronaQuotes();
				string = quote.returnCoronaQuote(coronaDamageInt, globuloDamageInt, coronaAttackHitRate, globuloAttackHitRate);
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
				commentID = utils.nextStringGlobuloDefense(globuloDefenceHitRate);
				string = utils.getString(commentID);
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
				commentID = utils.coronaDefense(coronaDefenceHitRate);
				string = utils.getString(commentID);
				nextCommentPermission = true;
				coronaLife -= utils.decreaseLife(globuloDamageInt, coronaDefenceHitRate);
				coronaLoseLife = true;
		}
			


		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		batch.begin();
		
		sprite.draw(batch);
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
			globuloLifeBar.rect(10,100,10,globuloLife);
			coronaLifeBar.rect(770,100,10,coronaLife);
		}

		
		

		
		batch.draw((TextureRegion)animationGlobulo.getKeyFrame(elapsedTime, true), globuloPosX, globuloPosY);
		batch.draw((TextureRegion)animationCommentBox.getKeyFrame(elapsedTime, true), -450, -525);
		if(globuloDefence){
				batch.draw((TextureRegion)animationGlobuloDef.getKeyFrame(elapsedTime, true), globuloPosX, globuloPosY);
				string = "			      *Defending*";
				
			}
				
		
		batch.draw((TextureRegion)animationCorona.getKeyFrame(elapsedTime, true), coronaPosX, coronaPosY);
		

		if(coronaDefence){
				batch.draw((TextureRegion)animationCoronaDef.getKeyFrame(elapsedTime, true), coronaPosX, coronaPosY);
				nextCommentPermission = false;
				string = "  *Corona Defending*";
		}
		if(basicAttackGlobulo && attackGlobuloPos < coronaPosX && globuloTurn){   
				batch.draw((TextureRegion)animationGlobuloAttack.getKeyFrame(elapsedTime, true), attackGlobuloPos, globuloPosY+50);
				attackGlobuloPos += 8;
				string = "      *Attacking*";
				
			}
			else if(globuloTurn && basicAttackGlobulo){
				commentID = utils.nextStringGlobuloAttack(globuloDamageInt, globuloAttackHitRate);
				string = utils.getString(commentID);
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
					System.out.println("Damage: "+ globuloDamageInt);
					System.out.println("Defence: "+ coronaDefenceHitRate);
					coronaDefenceHitRate = 0;
					coronaLife -= utils.decreaseLife(globuloDamageInt, coronaDefenceHitRate); 
					coronaLoseLife = true;
					System.out.println(utils.decreaseLife(globuloDamageInt, coronaDefenceHitRate));
				}
			}
			

			
			
		if(basicAttackCorona == true && attackCoronaPos > globuloPosX+100 && basicAttackCorona == true){
				batch.draw((TextureRegion)animationCoronaAttack.getKeyFrame(elapsedTime, true), attackCoronaPos, coronaPosY+50);
				attackCoronaPos -= 8;
				nextCommentPermission = false;
				string = "    *Corona Attack*";
		}
		else if(basicAttackCorona == true){
			basicAttackCorona = false;
			attackCoronaPos = coronaPosX;
			commentID = utils.coronaStringAttack(coronaAttackHitRate, coronaDamageInt);
			string = utils.getString(commentID);
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
			String scoreStr;
			showBar = false;
			Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			scoreStr = Integer.toString(utils.scoreGet(coronaLife, globuloLife));
			batch.begin();
            font.draw(batch, utils.matchResult(coronaLife, globuloLife), -130, 120);
            font.draw(batch, "Score:", -190, 21);
            font.draw(batch, scoreStr,  50, 21);
            font.draw(batch, "Press enter to restart.", Gdx.graphics.getWidth()/7-450, Gdx.graphics.getWidth()/8 -160);
			batch.end();
			
	}

	   
	   

	}

	
	@Override
	public void resize(final int width, final int height) {

	}
	
}
