import codecs
import shlex
import sys
import os
from subprocess import Popen, PIPE


def remove_permissions(glist, encpwd):
    """
    :param glist: List of Groups
    :param encpwd: Encoded password for Stash
    :return: None
    """

    password = codecs.decode(encpwd, 'rot_13')
    count = 0
    for group in glist:
        count += 1

        # Remove "Stash User" Permission to the list of groups!
        cmd = './stash.sh -s https://gitscm.cisco.com -u automation -p ' + password + \
              ' -a revokePermissions --group ' + group
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path())
        output, err = p.communicate()
        rc = p.returncode
        if rc > 0:
            print(err.decode('utf-8'), end='')
        else:
            print(output.decode('utf-8'), end='')
    final_wrap_up(count)


def grant_permissions(glist, encpwd):
    """
    :param glist: List of Groups
    :param encpwd: Encoded password for Stash
    :return: None
    """

    password = codecs.decode(encpwd, 'rot_13')
    count = 0
    for group in glist:
        count += 1

        # Add Stash User Permission to the list of groups!
        cmd = './stash.sh -s https://gitscm.cisco.com -u automation -p ' + password + \
              ' -a grantPermissions --group ' + group + ' --permission LICENSED_USER'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path())
        output, err = p.communicate()
        rc = p.returncode
        if rc > 0:
            print(err.decode('utf-8'), end='')
        else:
            print(output.decode('utf-8'), end='')
    final_wrap_up(count)


def folder_path():
    """
    :return: Folder Path to be used as current working directory
    """
    path = os.getcwd()
    return path


def final_wrap_up(c):
    print()
    print('+' * 100)
    print("Number of total groups whose permissions were revoked:", c)


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print('Usage: {} password'.format(sys.argv[0]))
        sys.exit(1)
    else:
        # You did not think I was going to give you the password that easy ;-)
        encoded_password = sys.argv[1]

        # Read the list of groups from the external text file
        f = open('grouplist.txt', 'r')
        grouplist = []
        for line in f:
            grouplist.append(line.rstrip())
        f.close()

        # Let's remove their "Stash User" Global permissions
        remove_permissions(grouplist, encoded_password)
        # grant_permissions(grouplist, encoded_password)