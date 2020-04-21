package data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedShow;
import com.wrapper.spotify.requests.data.library.GetUsersSavedShowsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersSavedShowsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetUsersSavedShowsRequest getUsersSavedShowsRequest = spotifyApi.getUsersSavedShows()
//          .limit(10)b
//          .offset(0)
    .build();

  public static void getUsersSavedShows_Sync() {
    try {
      final Paging<SavedShow> savedShowPaging = getUsersSavedShowsRequest.execute();

      System.out.println("Total: " + savedShowPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersSavedShows_Async() {
    try {
      final CompletableFuture<Paging<SavedShow>> pagingFuture = getUsersSavedShowsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<SavedShow> savedShowPaging = pagingFuture.join();

      System.out.println("Total: " + savedShowPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getUsersSavedShows_Sync();
    getUsersSavedShows_Async();
  }
}