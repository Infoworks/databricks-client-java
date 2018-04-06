package com.level11data.databricks.library;

import com.level11data.databricks.client.HttpException;
import com.level11data.databricks.client.LibrariesClient;
import com.level11data.databricks.client.entities.libraries.ClusterLibraryStatusesDTO;
import com.level11data.databricks.client.entities.libraries.LibraryFullStatusDTO;
import com.level11data.databricks.cluster.ClusterLibrary;
import com.level11data.databricks.cluster.InteractiveCluster;
import com.level11data.databricks.library.util.LibraryHelper;

import java.net.URI;

public class JarLibrary extends PrivateLibrary {
    private final LibrariesClient _client;

    public JarLibrary(LibrariesClient client, URI uri) throws LibraryConfigException {
        super(client, uri);
        _client = client;
    }

    public LibraryStatus getClusterStatus(InteractiveCluster cluster) throws LibraryConfigException {
        try {
            ClusterLibraryStatusesDTO libStatuses = _client.getClusterStatus(cluster.Id);

            //find library status for this library
            for (LibraryFullStatusDTO libStat : libStatuses.LibraryStatuses) {
                if(libStat.Library.Jar != null) {
                    if(libStat.Library.Jar.equals(this.Uri.toString())) {
                        return new LibraryStatus(libStat);
                    }
                }
            }
        } catch(HttpException e) {
            throw new LibraryConfigException(e);
        }
        throw new LibraryConfigException("Jar Library " + this.Uri.toString() +
                " Not Associated With Cluster Id " + cluster.Id);
    }

    public ClusterLibrary install(InteractiveCluster cluster) throws HttpException {
        _client.installLibraries(createLibraryRequest(cluster, LibraryHelper.createLibraryDTO(this)));
        return new ClusterLibrary(cluster, this);
    }

    public void uninstall(InteractiveCluster cluster) throws HttpException {
        _client.uninstallLibraries(createLibraryRequest(cluster, LibraryHelper.createLibraryDTO(this)));
    }
}
